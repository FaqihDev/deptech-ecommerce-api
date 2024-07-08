package com.jamsirat.atmapi.Mapper;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.user.ResponseDetailUserDto;
import com.jamsirat.atmapi.model.auth.User;
import org.springframework.stereotype.Component;


@Component
public class ResponseUserReverseMapper extends ADATAMapper<ResponseDetailUserDto, User> {
    @Override
    public User convert(ResponseDetailUserDto responseDetailUserDto) {
                User user = new User();
                user.setId(responseDetailUserDto.getId());
                user.setFirstName(responseDetailUserDto.getFirstName());
                user.setLastName(responseDetailUserDto.getLastName());
                return user;
    }
}
