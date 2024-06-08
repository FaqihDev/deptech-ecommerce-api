package com.jamsirat.atmapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamsirat.atmapi.dto.response.AuthenticationResponse;
import com.jamsirat.atmapi.dto.request.LoginRequest;
import com.jamsirat.atmapi.dto.request.RegistrationRequest;
import com.jamsirat.atmapi.dto.response.HttpResponse;
import com.jamsirat.atmapi.exception.*;
import com.jamsirat.atmapi.model.Role;
import com.jamsirat.atmapi.model.Token;
import com.jamsirat.atmapi.model.User;
import com.jamsirat.atmapi.repository.IRoleRepository;
import com.jamsirat.atmapi.repository.ITokenRepository;
import com.jamsirat.atmapi.repository.IUserRepository;
import com.jamsirat.atmapi.service.IAuthenticationService;
import com.jamsirat.atmapi.statval.enumeration.ETokenType;
import com.jamsirat.atmapi.statval.enumeration.EUserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    private final EmailValidatorServiceImpl emailValidatorService;



    @Override
    @Transactional
    public User register(RegistrationRequest request, HttpServletRequest httpServletRequest) {

        Set<Role>roles = new HashSet<>();
        EUserRole role = EUserRole.USER;
        try {
            Role userRole = roleRepository.findByUserRole(role);
            if (Objects.nonNull(userRole)) {
                roles.add(userRole);
            }
        } catch (DataNotFoundException e) {
            log.error("Error find Role by Code {} : {} " + EUserRole.USER, e.toString());
            throw new DataNotFoundException(404,"Role not found");
        }

           var existUser = userRepository.findByUserName(request.getEmail());
           if (existUser.isPresent()) {
               throw new UserAlreadyExistException("user is already taken","msg");
           }

           var validateEmail = emailValidatorService.validateAll(request.getEmail());
             if(validateEmail) {
                 var user = User.builder()
                         .firstName(request.getFirstName())
                         .lastName(request.getLastName())
                         .userName(request.getEmail())
                         .isActive(false)
                         .password(passwordEncoder.encode(request.getPassword()))
                         .roles(roles)
                         .build();
                 roleRepository.saveAll(roles);
                 var currentUser = userRepository.save(user);
                 var jwtToken = jwtService.generateToken(currentUser);
                 saveUserToken(currentUser,jwtToken);
                 return currentUser;
             } else {
                 throw new EmailNotValidException(404,"Email is not valid");
             }
    }



    @Override
    public void saveUserToken(User user, String token) {
        var userToken = Token.builder()
                .user(user)
                .token(token)
                .tokenType(ETokenType.BEARER)
                .isTokenExpired(false)
                .isRevoked(false)
                .build();
        tokenRepository.save(userToken);
    }

    @Override
    public HttpResponse<Object> login (LoginRequest loginRequest){
       try {
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
       } catch (UserNotActivatedException e) {
           log.info("Authentication failed : {} ", e.getMessage());
           throw new UserNotActivatedException("User is not activated yet");
       } catch (org.springframework.security.authentication.BadCredentialsException e) {
           throw new BadCredentialsException("Invalid username or password");
       } catch (UserNotFoundException e) {
           throw new UserNotFoundException("User is not found");
       }

       var user = userRepository.findByUserName(loginRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("username not found"));
       var jwtToken = jwtService.generateToken(user);
       var refreshToken = jwtService.refreshToken(user);

        String roles = user.getRoles().stream()
                .map(role -> role.getUserRole().getName())
                .collect(Collectors.joining(", "));
        AuthenticationResponse response = AuthenticationResponse.builder()
                        .name(user.getFirstName())
                        .accessToken(jwtToken)
                        .refreshToken(refreshToken)
                        .role(roles)
                        .isEnabled(user.isEnabled())
                        .build();

        revokeAllUserToken(user);
        saveUserToken(user,jwtToken);
        return HttpResponse.builder()
                .message("Profile we trust !")
                .timeStamp(LocalDateTime.now().toString())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .developerMessage("Login successfull")
                .data(response)
                .build();
    }

    @Override
    public AuthenticationResponse verifyEmail(String token) {
        var verifyToken  = tokenRepository.findByToken(token).orElseThrow(()-> new IllegalArgumentException("Invalid token"));
        if (verifyToken.getUser().isEnabled()) {
            throw new EmailAlreadyVerifiedException(401,"Email is already verified please login!");
        }


        if (jwtService.isTokenValid(token, verifyToken.getUser())) {
         verifyToken.getUser().setIsActive(true);
         tokenRepository.save(verifyToken);
         return AuthenticationResponse.builder()
                    .isEnabled(verifyToken.getUser().isEnabled())
                    .build();
        } else {
            throw new IllegalArgumentException("Invalid Token");
        }
    }

    @Override
    public String validateToken(String token) {
        var tokenVerifiy = tokenRepository.findByToken(token).orElseThrow(() -> new InvalidTokenException(404,"Token is invalid"));
        var user = tokenVerifiy.getUser();
        if (!jwtService.isTokenValid(tokenVerifiy.token, user)) {
            throw new InvalidTokenException(404,"Token is invalid");
        }

        user.setIsActive(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName()+ ":" + request.getServerPort()+request.getContextPath();
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



    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader("Authorization");
        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith(" Bearer")) {
            return;
        }

        //get refresh token from header
        refreshToken = authHeader.substring(7);

        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = userRepository.findByUserName(userEmail).orElseThrow(()-> new UsernameNotFoundException("Username not found exception"));
            //get valid token
            if (jwtService.isTokenValid(refreshToken,user)) {
                var accessToken  = jwtService.generateToken(user);
                revokeAllUserToken(user);
                saveUserToken(user,accessToken);
                var authResponse = AuthenticationResponse
                        .builder()
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(),authResponse);
            }
        }
    }
}