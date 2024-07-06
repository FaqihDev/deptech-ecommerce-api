package com.jamsirat.atmapi.service;

import com.jamsirat.atmapi.dto.response.user.GrantRoleAccessResponse;

import java.security.Principal;
import java.util.List;

public interface IAuthorizationService {
     GrantRoleAccessResponse giveAccessToUser(Long userId, Long roleId, Principal principal);

     List<String> getRolesByLoggedInUser(Principal principal);
}