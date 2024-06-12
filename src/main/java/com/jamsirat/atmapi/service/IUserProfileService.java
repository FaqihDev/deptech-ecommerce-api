package com.jamsirat.atmapi.service;

import com.jamsirat.atmapi.dto.request.CompleteOrUpdateUserProfileRequest;
import com.jamsirat.atmapi.dto.response.CompleteUserProfileResponse;

public interface IUserProfileService {

    CompleteUserProfileResponse completeUserProfile(CompleteOrUpdateUserProfileRequest request);

}