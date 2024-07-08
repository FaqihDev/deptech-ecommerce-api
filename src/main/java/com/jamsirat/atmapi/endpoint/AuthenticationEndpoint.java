package com.jamsirat.atmapi.endpoint;

import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.user.LoginRequest;
import com.jamsirat.atmapi.dto.request.user.RegistrationRequest;
import com.jamsirat.atmapi.service.impl.JwtService;
import com.jamsirat.atmapi.service.impl.AuthenticationServiceImpl;
import com.jamsirat.atmapi.service.impl.LogoutServiceImpl;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = IApplicationConstant.ContextPath.AUTHENTICATION,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationEndpoint {

    private final AuthenticationServiceImpl authenticationService;
    private final LogoutServiceImpl logoutService;
    private final JwtService jwtService;

    @PostMapping(IApplicationConstant.Path.Authentication.REGISTER)
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @PostMapping(IApplicationConstant.Path.Authentication.LOGIN)
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.login(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @PostMapping(IApplicationConstant.Path.Authentication.LOGOUT)
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            return ResponseEntity.ok(authenticationService.logout(request,response,authentication));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @PostMapping(IApplicationConstant.Path.Authentication.REFRESH_TOKEN)
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            authenticationService.refreshToken(request, response);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }
}