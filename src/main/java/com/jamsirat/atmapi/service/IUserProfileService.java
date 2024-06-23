package com.jamsirat.atmapi.service;

import com.jamsirat.atmapi.dto.request.CompleteOrUpdateUserProfileRequest;
import com.jamsirat.atmapi.dto.response.CompleteOrUpdateUserProfileResponse;
import com.jamsirat.atmapi.dto.response.UserProfilleDetailResponse;

public interface IUserProfileService {

    CompleteOrUpdateUserProfileResponse completeUserProfile(CompleteOrUpdateUserProfileRequest request);

    CompleteOrUpdateUserProfileResponse updateUserProfile(CompleteOrUpdateUserProfileRequest request);

    UserProfilleDetailResponse getDetailUserProfile(Long userId);



}