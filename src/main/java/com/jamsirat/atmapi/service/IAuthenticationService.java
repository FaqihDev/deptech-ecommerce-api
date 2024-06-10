package com.jamsirat.atmapi.service;

import com.jamsirat.atmapi.dto.request.LoginRequest;
import com.jamsirat.atmapi.dto.request.RegistrationRequest;
import com.jamsirat.atmapi.dto.response.AuthenticationResponse;
import com.jamsirat.atmapi.dto.response.HttpResponse;
import com.jamsirat.atmapi.model.auth.User;
import jakarta.servlet.http.HttpServletRequest;

public interface IAuthenticationService {

     User register(RegistrationRequest request, HttpServletRequest httpServletRequest);

     void saveUserToken(User user, String token);

     HttpResponse<Object> login (LoginRequest loginRequest);

     AuthenticationResponse verifyEmail(String token);

     String validateToken(String token);

     String applicationUrl(HttpServletRequest request);


}