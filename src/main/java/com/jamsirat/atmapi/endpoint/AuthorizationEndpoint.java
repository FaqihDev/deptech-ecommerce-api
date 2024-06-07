package com.jamsirat.atmapi.endpoint;


import com.jamsirat.atmapi.dto.request.GrantRoleAccessRequest;
import com.jamsirat.atmapi.dto.response.GrantRoleAccessResponse;
import com.jamsirat.atmapi.dto.response.HttpResponse;
import com.jamsirat.atmapi.service.IAuthorizationService;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = IApplicationConstant.ContextPath.AUTHORIZATION, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AuthorizationEndpoint {

    private final IAuthorizationService authorizationService;

    @PostMapping(IApplicationConstant.Path.Authorization.GRANT_ACCESS)
    ResponseEntity<?> grantAccess(@RequestBody GrantRoleAccessRequest request, Principal principal) {
        GrantRoleAccessResponse response = authorizationService.giveAccessToUser(request.getUserId(),request.getRoleId(), principal);
         return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .developerMessage(String.format("You are now %s", response.getRoleName()))
                        .message("Access has been granted")
                        .status(HttpStatus.CREATED)
                        .data(response)
                        .build());
    }

}