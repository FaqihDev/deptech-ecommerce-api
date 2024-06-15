package com.jamsirat.atmapi.service.impl;

import com.jamsirat.atmapi.dto.request.CompleteOrUpdateUserProfileRequest;
import com.jamsirat.atmapi.dto.response.CompleteUserProfileResponse;
import com.jamsirat.atmapi.exception.DataNotFoundException;
import com.jamsirat.atmapi.exception.EmailNotVerifiedException;
import com.jamsirat.atmapi.model.profile.Domicile;
import com.jamsirat.atmapi.model.profile.UserProfile;
import com.jamsirat.atmapi.model.profile.UserProfileExtended;
import com.jamsirat.atmapi.repository.IDomicileRepository;
import com.jamsirat.atmapi.repository.IUserProfileExtendedRepository;
import com.jamsirat.atmapi.repository.IUserProfileRepository;
import com.jamsirat.atmapi.repository.IUserRepository;
import com.jamsirat.atmapi.service.IUserProfileService;
import com.jamsirat.atmapi.statval.enumeration.EGender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserProfileServiceImpl implements IUserProfileService {

    private final IUserProfileRepository userProfileRepository;
    private final IUserProfileExtendedRepository userProfileExtendedRepository;
    private final IDomicileRepository domicileRepository;
    private final IUserRepository userRepository;


    @Override
    public CompleteUserProfileResponse completeUserProfile(CompleteOrUpdateUserProfileRequest request) {
        var userId = userRepository.findById(request.getUserId()).orElseThrow(()
                -> new DataNotFoundException(String.format("User with id %s is not found{}",request.getUserId()), "Please choose another user"));

        if (Boolean.FALSE.equals(userId.getIsActive())) {
            throw new EmailNotVerifiedException("Email is not verified!","Please verify you account");
        }

        //todo : handle once userprofile is already added

        var userProfile = UserProfile.builder()
                .user(userId)
                .isVerifiedUser(userId.getIsActive())
                .isEmailVerified(userId.getIsActive())
                .isMobilePhoneNumberVerified(false)
                .build();

        var userProfileExtended = UserProfileExtended.builder()
                .fullName(userId.getFirstName() + " " + userId.getLastName())
                .email(userId.getUsername())
                .userProfile(userProfile)
                .address(request.getAddress())
                .birthDate(request.getBirthDate())
                .birthPlace(request.getBirthPlace())
                .phoneNumber(request.getPhoneNumber())
                .gender(EGender.valueOf(request.getGender().toUpperCase()))
                .height(request.getHeight())
                .origin(request.getOrigin())
                .build();

        var domicile = Domicile.builder()
                .userProfileExtendedId(userProfileExtended)
                .desaSambung(request.getDesaSambung())
                .desaAddress(request.getDesaAddress())
                .kelompokSambung(request.getKelompokSambung())
                .kelompokAddress(request.getKelompokAddress())
                .build();

        userProfileRepository.save(userProfile);
        userProfileExtendedRepository.save(userProfileExtended);
        domicileRepository.save(domicile);

        return CompleteUserProfileResponse
                .builder()
                .userId(userId.getId())
                .fullName(userProfileExtended.getFullName())
                .build();
    }



}