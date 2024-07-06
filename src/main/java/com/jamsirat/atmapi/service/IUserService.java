package com.jamsirat.atmapi.service;

import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.user.RequestUpdateUserDto;
import jakarta.servlet.http.HttpServletRequest;

public interface IUserService {

    HttpResponse<Object> getListUsers();

    HttpResponse<Object> updateUsers(RequestUpdateUserDto request);

    HttpResponse<Object> deleteUsers(Long userId);

    HttpResponse<Object> getDetailUsers(HttpServletRequest request);
}
