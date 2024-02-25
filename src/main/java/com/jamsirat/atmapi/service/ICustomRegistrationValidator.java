package com.jamsirat.atmapi.service;


import com.jamsirat.atmapi.model.User;
import com.jamsirat.atmapi.statval.enumeration.EValidationResult;

import java.util.function.Function;

public interface ICustomRegistrationValidator extends Function<User, EValidationResult> {

      ICustomRegistrationValidator isPhoneNumberValid();

      ICustomRegistrationValidator isEmailValid();

      ICustomRegistrationValidator and(ICustomRegistrationValidator other);

}