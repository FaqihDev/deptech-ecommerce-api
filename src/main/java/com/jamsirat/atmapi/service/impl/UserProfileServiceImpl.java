package com.jamsirat.atmapi.service.impl;

import com.jamsirat.atmapi.dto.request.CompleteOrUpdateUserProfileRequest;
import com.jamsirat.atmapi.dto.response.CompleteOrUpdateUserProfileResponse;
import com.jamsirat.atmapi.exception.DataNotFoundException;
import com.jamsirat.atmapi.exception.EmailNotVerifiedException;
import com.jamsirat.atmapi.exception.UserProfileAlreadyAddedException;
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
    public CompleteOrUpdateUserProfileResponse completeUserProfile(CompleteOrUpdateUserProfileRequest request) {

        //todo : handle once userprofile is already added
        var userProfileByUserId = userProfileRepository.findByUserId(request.getUserId()).orElseThrow(() -> new UserProfileAlreadyAddedException(String.format("Profile with user %s is already added",request.getUserId()),"Please go to update feature to update your data"));
        var userId = userRepository.findById(request.getUserId()).orElseThrow(()-> new DataNotFoundException(String.format("User with id %s is not found{}",request.getUserId()), "Please choose another user"));

            if (Boolean.FALSE.equals(userId.getIsActive())) {
                throw new EmailNotVerifiedException("Email is not verified!","Please verify you account");
            }

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

        return CompleteOrUpdateUserProfileResponse
                .builder()
                .userId(userProfileByUserId.getUser().getId())
                .fullName(userProfileByUserId.getUser().getFirstName() + " " + userProfileByUserId.getUser().getLastName())
                .address(userProfileExtended.getAddress())
                .gender(userProfileExtended.getGender().name())
                .height(userProfileExtended.getHeight())
                .birthDate(userProfileExtended.getBirthDate())
                .birthPlace(userProfileExtended.getBirthPlace())
                .phoneNumber(userProfileExtended.getPhoneNumber())
                .origin(userProfileExtended.getOrigin())
                .desaSambung(domicile.getDesaSambung())
                .desaAddress(domicile.getDesaAddress())
                .kelompokSambung(domicile.getKelompokSambung())
                .kelompokAddress(domicile.getKelompokAddress())
                .build();
    }



    public CompleteOrUpdateUserProfileResponse updateUserProfile(CompleteOrUpdateUserProfileRequest request) {
        var userProfileByUserId = userProfileRepository.findByUserId(request.getUserId());
        var userProfileExtended = userProfileExtendedRepository.findByUserProfile(userProfileByUserId).orElseThrow(() -> new DataNotFoundException("User profile is not found", "please check your database"));
        var domicile = domicileRepository.findByUserProfileExtendedId(userProfileExtended).orElseThrow(() -> new DataNotFoundException("Domicile is not found", "please check your database"));

        userProfileExtended.setAddress(request.getAddress());
        userProfileExtended.setBirthDate(request.getBirthDate());
        userProfileExtended.setBirthPlace(request.getBirthPlace());
        userProfileExtended.setOrigin(request.getOrigin());
        userProfileExtended.setPhoneNumber(request.getPhoneNumber());
        userProfileExtended.setHeight(request.getHeight());

        domicile.setKelompokSambung(request.getKelompokSambung());
        domicile.setKelompokAddress(request.getKelompokAddress());
        domicile.setDesaAddress(request.getDesaAddress());
        domicile.setDesaSambung(request.getDesaSambung());

        userProfileExtendedRepository.save(userProfileExtended);
        domicileRepository.save(domicile);

        return CompleteOrUpdateUserProfileResponse.builder()
                .userId(userProfileByUserId.get().getUser().getId())
                .fullName(userProfileByUserId.get().getUser().getFirstName() + " " + userProfileByUserId.get().getUser().getLastName())
                .address(userProfileExtended.getAddress())
                .gender(userProfileExtended.getGender().name())
                .height(userProfileExtended.getHeight())
                .birthDate(userProfileExtended.getBirthDate())
                .birthPlace(userProfileExtended.getBirthPlace())
                .phoneNumber(userProfileExtended.getPhoneNumber())
                .origin(userProfileExtended.getOrigin())
                .desaSambung(domicile.getDesaSambung())
                .desaAddress(domicile.getDesaAddress())
                .kelompokSambung(domicile.getKelompokSambung())
                .kelompokAddress(domicile.getKelompokAddress())
                .build();
    }
}