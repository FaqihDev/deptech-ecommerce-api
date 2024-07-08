package com.jamsirat.atmapi.endpoint;


import com.jamsirat.atmapi.dto.request.user.LoginRequest;
import com.jamsirat.atmapi.dto.request.user.RegistrationRequest;
import com.jamsirat.atmapi.service.IAuthenticationService;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping(value = IApplicationConstant.ContextPath.AUTHENTICATION,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationEndpoint {

    private final IAuthenticationService authenticationService;

    @PostMapping(IApplicationConstant.Path.Authentication.REGISTER)
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping(IApplicationConstant.Path.Authentication.LOGIN)
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping(IApplicationConstant.Path.Authentication.LOGOUT)
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        return ResponseEntity.ok(authenticationService.logout(request, response, authentication));
    }

    @PostMapping(IApplicationConstant.Path.Authentication.REFRESH_TOKEN)
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
        return ResponseEntity.ok().build();
    }

}