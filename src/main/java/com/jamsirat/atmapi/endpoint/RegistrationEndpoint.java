package com.jamsirat.atmapi.endpoint;

import com.jamsirat.atmapi.dto.request.LoginRequest;
import com.jamsirat.atmapi.dto.request.RegistrationRequest;
import com.jamsirat.atmapi.dto.response.AuthenticationResponse;
import com.jamsirat.atmapi.dto.response.HttpResponse;
import com.jamsirat.atmapi.event.RegistrationCompleteEvent;
import com.jamsirat.atmapi.repository.ITokenRepository;
import com.jamsirat.atmapi.service.impl.JwtService;
import com.jamsirat.atmapi.service.impl.AuthenticationServiceImpl;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;
import java.util.concurrent.CompletableFuture;

import com.jamsirat.atmapi.statval.enumeration.EUserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = IApplicationConstant.ContextPath.AUTHENTICATION,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationEndpoint {

    private final AuthenticationServiceImpl authenticationService;
    private final ITokenRepository tokenRepository;
    private final JwtService jwtService;
    private final ApplicationEventPublisher publisher;

    @PostMapping(IApplicationConstant.Path.Authentication.REGISTER)
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request, HttpServletRequest httpServletRequest) throws ExecutionException, InterruptedException {
        var user = authenticationService.register(request,httpServletRequest);

        CompletableFuture<String>  accessTokenFuture = CompletableFuture.supplyAsync(()-> jwtService.generateToken(user));
        CompletableFuture<String>  refreshTokenFuture = CompletableFuture.supplyAsync(()-> jwtService.refreshToken(user));

        publisher.publishEvent(new RegistrationCompleteEvent(user, authenticationService.applicationUrl(httpServletRequest)));

        AuthenticationResponse response = AuthenticationResponse.builder()
                .name(user.getFirstName() + " " + user.getLastName())
                .isEnabled(user.getIsActive())
                .accessToken(accessTokenFuture.get())
                .refreshToken(refreshTokenFuture.get())
                .role(EUserRole.USER.getName())
                .build();

        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .developerMessage("Verify your account ! Verification link was sent to your email")
                        .message("User created")
                        .status(HttpStatus.CREATED)
                        .data(response)
                        .build()
        );

    }

    @GetMapping(value = IApplicationConstant.Path.Authentication.LOGIN)
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @GetMapping(value = IApplicationConstant.Path.Authentication.VERIFY_ACCOUNT)
    public ResponseEntity<HttpResponse<?>> verifyEmail(@RequestParam("token") String token) {
        authenticationService.verifyEmail(token);
        return ResponseEntity.ok(HttpResponse.builder()
                .status(HttpStatus.OK)
                .message("email verified successfull")
                .developerMessage("Please login to your account!")
                .timeStamp(LocalDateTime.now().toString())
                .statusCode(HttpStatus.OK.value())
                .build());

    }




    @PostMapping(IApplicationConstant.Path.Authentication.REFRESH_TOKEN)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request,response);
    }

}