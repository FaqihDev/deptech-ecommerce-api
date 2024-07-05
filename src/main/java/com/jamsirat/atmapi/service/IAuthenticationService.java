package com.jamsirat.atmapi.service;

import com.jamsirat.atmapi.dto.request.LoginRequest;
import com.jamsirat.atmapi.dto.request.RegistrationRequest;
import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.RequestUpdateUserDto;
import com.jamsirat.atmapi.model.auth.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface IAuthenticationService {

     HttpResponse<Object> register(RegistrationRequest request);

     void saveUserToken(User user, String token);

     HttpResponse<Object> login (LoginRequest loginRequest);

     String validateToken(String token);

     HttpResponse<Object> getListUsers();

     HttpResponse<Object> updateUsers(RequestUpdateUserDto request);

     HttpResponse<Object> deleteUsers(Long userId);

     HttpResponse<Object> getDetailUsers(HttpServletRequest request);

}