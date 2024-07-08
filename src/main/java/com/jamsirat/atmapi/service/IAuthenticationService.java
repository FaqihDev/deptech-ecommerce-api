package com.jamsirat.atmapi.service;

import com.jamsirat.atmapi.dto.request.user.LoginRequest;
import com.jamsirat.atmapi.dto.request.user.RegistrationRequest;
import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.model.auth.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

import java.io.IOException;

public interface IAuthenticationService {

     HttpResponse<Object> register(RegistrationRequest request);

     void saveUserToken(User user, String token);

     HttpResponse<Object> login (LoginRequest loginRequest);

     String validateToken(String token);

     HttpResponse<?> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication);

     void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}