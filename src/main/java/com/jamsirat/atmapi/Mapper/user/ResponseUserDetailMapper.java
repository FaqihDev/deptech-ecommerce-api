package com.jamsirat.atmapi.Mapper.user;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.user.ResponseDetailUserDto;
import com.jamsirat.atmapi.model.auth.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ResponseUserDetailMapper extends ADATAMapper<User, ResponseDetailUserDto> {
    @Override
    public ResponseDetailUserDto convert(User source) {

        return ResponseDetailUserDto.builder()
                .role(source.getRoles().stream()
                        .map(x -> x.getUserRole().getName())
                        .collect(Collectors.joining(",")))
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .build();
    }
}
