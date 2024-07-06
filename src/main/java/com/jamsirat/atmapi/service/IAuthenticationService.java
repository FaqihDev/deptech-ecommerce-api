package com.jamsirat.atmapi.service;

import com.jamsirat.atmapi.dto.request.user.LoginRequest;
import com.jamsirat.atmapi.dto.request.user.RegistrationRequest;
import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.model.auth.User;

public interface IAuthenticationService {

     HttpResponse<Object> register(RegistrationRequest request);

     void saveUserToken(User user, String token);

     HttpResponse<Object> login (LoginRequest loginRequest);

     String validateToken(String token);



}