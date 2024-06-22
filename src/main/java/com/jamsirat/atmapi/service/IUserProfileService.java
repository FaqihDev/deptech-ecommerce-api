package com.jamsirat.atmapi.service;

import com.jamsirat.atmapi.dto.request.CompleteOrUpdateUserProfileRequest;
import com.jamsirat.atmapi.dto.response.CompleteOrUpdateUserProfileResponse;

public interface IUserProfileService {

    CompleteOrUpdateUserProfileResponse completeUserProfile(CompleteOrUpdateUserProfileRequest request);

    CompleteOrUpdateUserProfileResponse updateUserProfile(CompleteOrUpdateUserProfileRequest request);

}