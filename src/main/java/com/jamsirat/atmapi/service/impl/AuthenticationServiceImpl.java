package com.jamsirat.atmapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamsirat.atmapi.dto.request.user.LoginRequest;
import com.jamsirat.atmapi.dto.request.user.RegistrationRequest;
import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.response.user.ResponseLoginDto;
import com.jamsirat.atmapi.dto.response.user.ResponseRegistrationDto;
import com.jamsirat.atmapi.exception.*;
import com.jamsirat.atmapi.model.auth.Role;
import com.jamsirat.atmapi.model.auth.Token;
import com.jamsirat.atmapi.model.auth.User;
import com.jamsirat.atmapi.repository.IRoleRepository;
import com.jamsirat.atmapi.repository.ITokenRepository;
import com.jamsirat.atmapi.repository.IUserRepository;
import com.jamsirat.atmapi.service.IAuthenticationService;
import com.jamsirat.atmapi.statval.enumeration.EGender;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.SuccessMessage;

import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.DeveloperSuccessMessage;

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
    private final LogoutServiceImpl logoutService;

    @Override
    @Transactional
    public HttpResponse<Object> register(RegistrationRequest request) {

        Set<Role>roles = new HashSet<>();
        EUserRole role = EUserRole.ROLE_USER;
        try {
            Role userRole = roleRepository.findByUserRole(role);
            if (Objects.nonNull(userRole)) {
                roles.add(userRole);
            }
        } catch (DataNotFoundException e) {
            log.error("Error find Role by Code {} : {} %s".formatted(EUserRole.ROLE_USER), e.toString());
            throw new DataNotFoundException("Role not found","Please check your database");
        }

           var userByEmail = userRepository.findByEmail(request.getEmail());
           if (userByEmail.isPresent()) {
               throw new UserAlreadyExistException("user is already taken","Please choose another email!");
           }
               var user = User.builder()
                       .firstName(request.getFirstName())
                       .lastName(request.getLastName())
                       .email(request.getEmail())
                       .password(passwordEncoder.encode(request.getPassword()))
                       .roles(roles)
                       .isActive(true)
                       .gender(EGender.valueOf(request.getGender()))
                       .build();
               roleRepository.saveAll(roles);
               var currentUser = userRepository.save(user);
               var jwtToken = jwtService.generateToken(currentUser);
               saveUserToken(currentUser,jwtToken);


        ResponseRegistrationDto response = ResponseRegistrationDto.builder()
                .name(user.getFirstName() + " " + user.getLastName())
                .role(EUserRole.ROLE_USER.getName())
                .email(user.getEmail())
                .build();

        return HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .developerMessage("Verify your account ! Verification link was sent to your email")
                        .message("User created")
                        .statusCode(HttpStatus.CREATED.value())
                        .status(HttpStatus.CREATED)
                        .data(response)
                        .build();

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
           throw new UserNotActivatedException("User is not activated yet", "Please verify your account");
       } catch (org.springframework.security.authentication.BadCredentialsException e) {
           throw new BadCredentialsException("Invalid username or password");
       } catch (UserNotFoundException e) {
           throw new UserNotFoundException("User is not found");
       }

       var user = userRepository.findByEmail(loginRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("username not found"));
       var jwtToken = jwtService.generateToken(user);
       var refreshToken = jwtService.refreshToken(user);

        String roles = user.getRoles().stream()
                .map(role -> role.getUserRole().getName())
                .collect(Collectors.joining(", "));

        ResponseLoginDto response = ResponseLoginDto.builder()
                        .name(user.getFirstName())
                        .role(roles)
                        .email(user.getEmail())
                        .accessToken(jwtToken)
                        .refreshToken(refreshToken)
                        .build();

        revokeAllUserToken(user);
        saveUserToken(user,jwtToken);
        return HttpResponse.builder()
                .message("Profile we trust !")
                .timeStamp(LocalDateTime.now().toString())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .developerMessage("Login successfully")
                .data(response)
                .build();
    }

    @Override
    public String validateToken(String token) {
        var tokenVerifiy = tokenRepository.findByToken(token).orElseThrow(() -> new InvalidTokenException("Token is invalid","Please do login"));
        var user = tokenVerifiy.getUser();
        if (!jwtService.isTokenValid(tokenVerifiy.token, user)) {
            throw new InvalidTokenException("Token is invalid","Please do login");
        }

        user.setIsActive(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public HttpResponse<?> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logoutService.logout(request,response,authentication);
        return HttpResponse.buildHttpResponse(SuccessMessage.LOGOUT_MSG_SUCCESS
                ,DeveloperSuccessMessage.LOGOUT_MSG_SUCCESS,HttpStatus.OK,HttpStatus.OK.value(), null);
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
            var user = userRepository.findByEmail(userEmail).orElseThrow(()-> new UsernameNotFoundException("Username not found exception"));
            //get valid token
            if (jwtService.isTokenValid(refreshToken,user)) {
                var accessToken  = jwtService.generateToken(user);
                revokeAllUserToken(user);
                saveUserToken(user,accessToken);
                var authResponse = ResponseLoginDto
                        .builder()
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(),authResponse);
            }
        }
    }
}