package com.jamsirat.atmapi;


import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.BaseMapper.ConvertResponseEntity;
import com.jamsirat.atmapi.dto.response.CompleteOrUpdateUserProfileResponse;
import com.jamsirat.atmapi.model.profile.Domicile;
import com.jamsirat.atmapi.model.profile.UserProfileExtended;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper extends ADATAMapper<UserProfileMapper.Request, CompleteOrUpdateUserProfileResponse> {


    @Override
    public CompleteOrUpdateUserProfileResponse convert(Request request) {
        return null;
    }


    public class Request {
        private UserProfileExtended userProfileExtended;
        private Domicile domicile;
    }


}