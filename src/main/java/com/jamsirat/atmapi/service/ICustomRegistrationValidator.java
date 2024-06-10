package com.jamsirat.atmapi.service;



import com.jamsirat.atmapi.model.profile.UserProfile;
import com.jamsirat.atmapi.statval.enumeration.EValidationResult;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public interface ICustomRegistrationValidator extends Function<UserProfile, EValidationResult> {

      ICustomRegistrationValidator isPhoneNumberValid();

      ICustomRegistrationValidator isEmailValid();

      ICustomRegistrationValidator and(ICustomRegistrationValidator other);

}