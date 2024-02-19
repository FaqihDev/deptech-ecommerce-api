package com.jamsirat.atmapi.service;

import com.jamsirat.atmapi.dto.AuthenticationResponse;
import com.jamsirat.atmapi.dto.request.LoginRequest;
import com.jamsirat.atmapi.dto.request.RegistrationRequest;
import com.jamsirat.atmapi.model.User;

public interface IAuthenticationService {

     AuthenticationResponse register(RegistrationRequest request);

     void saveUserToken(User user, String token);

     AuthenticationResponse login (LoginRequest loginRequest);


}
