package com.jamsirat.atmapi.mapper;


import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.CompleteOrUpdateUserProfileResponse;
import com.jamsirat.atmapi.model.profile.Domicile;
import com.jamsirat.atmapi.model.profile.UserProfile;
import com.jamsirat.atmapi.model.profile.UserProfileExtended;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper extends ADATAMapper<UserProfileMapper.Request, CompleteOrUpdateUserProfileResponse> {


    @Override
    public CompleteOrUpdateUserProfileResponse convert(Request request) {
        return CompleteOrUpdateUserProfileResponse.builder()
                .userId(request.userProfile.getUser().getId())
                .fullName(request.userProfile.getUser().getFirstName() + " " + request.userProfile.getUser().getLastName())
                .address(request.userProfileExtended.getAddress())
                .birthDate(request.userProfileExtended.getBirthDate())
                .birthPlace(request.userProfileExtended.getBirthPlace())
                .gender(request.userProfileExtended.getGender().getName())
                .origin(request.userProfileExtended.getOrigin())
                .phoneNumber(request.userProfileExtended.getPhoneNumber())
                .kelompokSambung(request.domicile.getKelompokSambung())
                .kelompokAddress(request.domicile.getKelompokAddress())
                .desaSambung(request.domicile.getDesaSambung())
                .desaAddress(request.domicile.getDesaAddress())
                .height(request.userProfileExtended.getHeight())
                .build();

    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Request {
        private UserProfile userProfile;
        private UserProfileExtended userProfileExtended;
        private Domicile domicile;
    }
}