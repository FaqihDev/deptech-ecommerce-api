package com.jamsirat.atmapi.service.impl;


import com.jamsirat.atmapi.model.User;
import com.jamsirat.atmapi.service.ICustomRegistrationValidator;
import com.jamsirat.atmapi.statval.enumeration.EValidationResult;

public class CustomRegistrationValidatorServiceImpl implements ICustomRegistrationValidator  {


      @Override
      public ICustomRegistrationValidator isPhoneNumberValid() {
            return
      }

      @Override
      public ICustomRegistrationValidator isEmailValid() {
            return null;
      }

      @Override
      public ICustomRegistrationValidator and(ICustomRegistrationValidator other) {
            return null;
      }

      @Override
      public EValidationResult apply(User user) {
            return null;
      }
}