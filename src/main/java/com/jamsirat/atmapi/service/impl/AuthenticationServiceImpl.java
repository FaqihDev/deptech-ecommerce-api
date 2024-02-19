package com.jamsirat.atmapi.service.impl;

import com.jamsirat.atmapi.dto.AuthenticationResponse;
import com.jamsirat.atmapi.dto.request.LoginRequest;
import com.jamsirat.atmapi.dto.request.RegistrationRequest;
import com.jamsirat.atmapi.exception.DataNotFoundException;
import com.jamsirat.atmapi.model.Role;
import com.jamsirat.atmapi.model.Token;
import com.jamsirat.atmapi.model.User;
import com.jamsirat.atmapi.repository.IRoleRepository;
import com.jamsirat.atmapi.repository.ITokenRepository;
import com.jamsirat.atmapi.repository.IUserRepository;
import com.jamsirat.atmapi.service.IAuthenticationService;
import com.jamsirat.atmapi.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final IUserRepository userRepository;
    private final ITokenRepository tokenRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse register(RegistrationRequest request) {

        Set<Role>roles = new HashSet<>();
        try {
            roles.add(roleRepository.findByUserRole(request.getRoleName()));
        } catch (DataNotFoundException e) {
            log.error("Error find Role by Code {} : {} " + request.getRoleName(), e.toString());
            throw new DataNotFoundException(404,"Role not found");
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .userName(request.getEmail())
                .isActive(false)
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();

       var currentUser = userRepository.save(user);
       var jwtToken = jwtService.generateToken(currentUser);
       var refreshToken = jwtService.refreshToken(currentUser);
       saveUserToken(currentUser,jwtToken);

       return AuthenticationResponse.builder()
               .accessToken(jwtToken)
               .name(currentUser.getFirstName())
               .refreshToken(refreshToken)
               .build();

    }

    @Override
    public void saveUserToken(User user, String token) {
        var userToken = Token.builder()
                .user(user)
                .token(token)
                .isTokenExpired(false)
                .isRevoked(false)
                .build();
        tokenRepository.save(userToken);
    }

    @Override
    public AuthenticationResponse login (LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        var user = userRepository.findByUserName(loginRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("username not found"));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.refreshToken(user);

        revokeAllUserToken(user);
        saveUserToken(user,jwtToken);
        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void revokeAllUserToken(User user) {
        var validTokenUsers = tokenRepository.findAllByValidToken(user.getId());
        if (validTokenUsers.isEmpty()) {
            return;
        }

        validTokenUsers.forEach(token -> {
            token.setTokenExpired(true);
            token.setRevoked(true);
        });

        tokenRepository.saveAll(validTokenUsers);
    }
}
