package com.jamsirat.atmapi.service;

import com.jamsirat.atmapi.dto.request.LoginRequest;
import com.jamsirat.atmapi.dto.request.RegistrationRequest;
import com.jamsirat.atmapi.dto.response.HttpResponse;
import com.jamsirat.atmapi.model.User;

public interface IAuthenticationService {

     User register(RegistrationRequest request);

     void saveUserToken(User user, String token);

     HttpResponse<Object> login (LoginRequest loginRequest);


}