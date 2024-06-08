package com.jamsirat.atmapi.service.impl;


import com.jamsirat.atmapi.service.EmailValidatorService;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class EmailValidatorServiceImpl implements EmailValidatorService {


    @Override
    public boolean validateByString(String pEmail) {
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+";
        pEmail = pEmail.trim();
        return pEmail.matches(emailPattern);
    }

    @Override
    public boolean validateByInternet(String p_email){
        try {
            InternetAddress internetAddress = new InternetAddress(p_email);
            internetAddress.validate();
            return true;
        } catch (AddressException e) {
            return false;
        }
    }

    @Override
    public boolean validateByStringAndInternet(String pEmail) {
        return validateByInternet(pEmail) && validateByString(pEmail);
    }

    @Override
    public boolean validateByDomain(String p_Email) {
        try {
            InetAddress.getByName(p_Email.substring(p_Email.lastIndexOf("@") + 1));
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }

    @Override
    public boolean validateAll(String pEmail) {
        return  validateByDomain(pEmail) && validateByStringAndInternet(pEmail);
    }

}