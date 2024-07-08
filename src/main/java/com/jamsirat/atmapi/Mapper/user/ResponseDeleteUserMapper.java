package com.jamsirat.atmapi.Mapper.user;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.product.ResponseDeleteProductDto;
import com.jamsirat.atmapi.model.auth.User;
import org.springframework.stereotype.Component;


@Component
public class ResponseDeleteUserMapper extends ADATAMapper<User, ResponseDeleteProductDto> {
    @Override
    public ResponseDeleteProductDto convert(User source) {

        return ResponseDeleteProductDto.builder()
                .id(source.getId())
                .build();
    }
}
