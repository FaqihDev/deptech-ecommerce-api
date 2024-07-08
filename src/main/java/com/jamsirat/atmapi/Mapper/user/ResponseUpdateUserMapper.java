package com.jamsirat.atmapi.Mapper.user;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.user.ResponseUpdateUserDto;
import com.jamsirat.atmapi.model.auth.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ResponseUpdateUserMapper extends ADATAMapper<User, ResponseUpdateUserDto> {
    @Override
    public ResponseUpdateUserDto convert(User source) {

        return ResponseUpdateUserDto.builder()
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
