package com.jamsirat.atmapi.endpoint;

import com.jamsirat.atmapi.dto.request.LoginRequest;
import com.jamsirat.atmapi.dto.request.RegistrationRequest;
import com.jamsirat.atmapi.dto.response.AuthenticationResponse;
import com.jamsirat.atmapi.dto.response.HttpResponse;
import com.jamsirat.atmapi.service.impl.JwtService;
import com.jamsirat.atmapi.service.impl.AuthenticationServiceImpl;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = IApplicationConstant.ContextPath.AUTHENTICATION,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationEndpoint {

    private final AuthenticationServiceImpl authenticationService;
    private final JwtService jwtService;

    @PostMapping(IApplicationConstant.Path.Authentication.REGISTER)
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        var user = authenticationService.register(request);
        var tokenUser = jwtService.generateToken(user);
        var refreshToken = jwtService.refreshToken(user);



        AuthenticationResponse response = AuthenticationResponse.builder()
                .name(user.getFirstName() + " " + user.getLastName())
                .isEnabled(user.getIsActive())
                .accessToken(tokenUser)
                .refreshToken(refreshToken)
                .build();

        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .developerMessage("Please login to your account")
                        .message("User created")
                        .status(HttpStatus.CREATED)
                        .data(response)
                        .build()
        );

    }

    @GetMapping(value = IApplicationConstant.Path.Authentication.LOGIN,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping(IApplicationConstant.Path.Authentication.REFRESH_TOKEN)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request,response);
    }

}