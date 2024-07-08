package com.jamsirat.atmapi.service.impl;

import com.jamsirat.atmapi.exception.UserNotActivatedException;
import com.jamsirat.atmapi.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.ExceptionMessage;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.DeveloperExceptionMessage;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomUserDetailService implements UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException(ExceptionMessage.USERNAME_NOT_FOUND));

        if (!user.getIsActive()) {
            try {
                throw new UserNotActivatedException(ExceptionMessage.USER_NOT_ACTIVATED_EXCEPTION,DeveloperExceptionMessage.USER_NOT_ACTIVATED_EXCEPTION);
            } catch (UserNotActivatedException e) {
                throw new RuntimeException(e);
            }
        }
        return new User(user.getUsername(),user.getPassword(),user.getAuthorities());
    }
}