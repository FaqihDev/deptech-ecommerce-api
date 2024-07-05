package com.jamsirat.atmapi.endpoint;

import com.jamsirat.atmapi.dto.request.LoginRequest;
import com.jamsirat.atmapi.dto.request.RegistrationRequest;
import com.jamsirat.atmapi.service.impl.JwtService;
import com.jamsirat.atmapi.service.impl.AuthenticationServiceImpl;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = IApplicationConstant.ContextPath.AUTHENTICATION,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationEndpoint {

    private final AuthenticationServiceImpl authenticationService;
    private final JwtService jwtService;

    @PostMapping(IApplicationConstant.Path.Authentication.REGISTER)
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) throws ExecutionException, InterruptedException {
       return ResponseEntity.ok(authenticationService.register(request));
    }

    @GetMapping(value = IApplicationConstant.Path.Authentication.LOGIN)
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping(IApplicationConstant.Path.Authentication.REFRESH_TOKEN)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request,response);
    }

    @GetMapping(IApplicationConstant.Path.Authentication.USER_DETAIL)
    public ResponseEntity<?> detailUser(HttpServletRequest request) {
       return ResponseEntity.ok(authenticationService.getDetailUsers(request));
    }

}