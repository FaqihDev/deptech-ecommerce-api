package com.jamsirat.atmapi.service;

import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.user.RequestUpdateUserDto;
import com.jamsirat.atmapi.dto.response.user.ResponseDetailUserDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface IUserService {

    HttpResponse<List<ResponseDetailUserDto>> getListUsers();

    HttpResponse<Object> updateUsers(RequestUpdateUserDto request);

    HttpResponse<Object> deleteUsers(Long userId);

    HttpResponse<Object> getDetailUsers(HttpServletRequest request);
}
