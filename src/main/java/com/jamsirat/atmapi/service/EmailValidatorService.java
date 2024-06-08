package com.jamsirat.atmapi.service;

public interface EmailValidatorService {


    boolean validateByString(String pEmail);

    boolean validateByInternet(String p_email);

    boolean validateByStringAndInternet(String pEmail);

    boolean validateByDomain(String p_Email);

    boolean validateAll(String pEmail);
}
