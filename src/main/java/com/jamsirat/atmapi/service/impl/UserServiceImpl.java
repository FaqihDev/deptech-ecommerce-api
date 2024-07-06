package com.jamsirat.atmapi.service.impl;


import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.user.RequestUpdateUserDto;
import com.jamsirat.atmapi.dto.response.user.ResponseDetailUserDto;
import com.jamsirat.atmapi.dto.response.user.ResponseUpdateUserDto;
import com.jamsirat.atmapi.exception.DataNotFoundException;
import com.jamsirat.atmapi.exception.IllegalHeaderException;
import com.jamsirat.atmapi.model.auth.Role;
import com.jamsirat.atmapi.model.auth.User;
import com.jamsirat.atmapi.repository.IUserRepository;
import com.jamsirat.atmapi.service.IUserService;
import com.jamsirat.atmapi.util.MapperUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public HttpResponse<Object> getListUsers() {
        List<User> users = userRepository.findAll();
        List<ResponseDetailUserDto> listUsers =   users.stream()
                .map(user -> {
                    ResponseDetailUserDto dto = MapperUtil.parse(user, ResponseDetailUserDto.class,MatchingStrategies.STRICT);
                    dto.setRole(user.getRoles().stream().map(Role::getRoleName).collect(Collectors.joining(", ")));
                    return dto;
                }).collect(Collectors.toList());

        return  HttpResponse.buildHttpResponse("Data fetched Successfully",
                "Data Fetched",
                HttpStatus.OK,
                HttpStatus.OK.value(),
                listUsers);

    }

    @Override
    public HttpResponse<Object> updateUsers(RequestUpdateUserDto request) {
        var user = userRepository.findById(request.getUserId()).orElseThrow(() ->  new DataNotFoundException(String.format("user with id %d is not exist", request.getUserId()),"please check again your user id"));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        userRepository.save(user);

        ResponseUpdateUserDto responseData =  MapperUtil.parse(user, ResponseUpdateUserDto.class, MatchingStrategies.STRICT);
        return HttpResponse.buildHttpResponse("Data has been updated",
                "Data saved",
                 HttpStatus.OK,
                 HttpStatus.OK.value(),
                 responseData);

        }

    @Override
    public HttpResponse<Object> deleteUsers(Long userId) {
        var userById = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException(String.format("user with id %d is not exist", userId),"please check again your user id"));
        userById.setIsDeleted(true);
        userRepository.save(userById);
        return HttpResponse.buildHttpResponse("Data has been deleted",
                "Data deleted",
                HttpStatus.OK,
                HttpStatus.OK.value(),
                null);
    }

    @Override
    public HttpResponse<Object> getDetailUsers(HttpServletRequest request) {

        User user = null;
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.nonNull(authHeader) && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            user = userRepository.findByToken(token);

            String roles = user.getRoles().stream()
                    .map(role -> role.getUserRole().getName())
                    .collect(Collectors.joining(", "));
            ResponseDetailUserDto data = MapperUtil.parse(user,ResponseDetailUserDto.class,MatchingStrategies.STRICT);
            data.setRole(roles);

            return HttpResponse.buildHttpResponse("Detail users fetched success",
                    "Data fetched",
                    HttpStatus.OK,
                    HttpStatus.OK.value(),
                    data);

        }
        throw new IllegalHeaderException("Authorization header and Bearer is not set","Please check your header");
    }

}
