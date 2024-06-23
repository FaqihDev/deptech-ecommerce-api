package com.jamsirat.atmapi.service.impl;

import com.jamsirat.atmapi.dto.response.GrantRoleAccessResponse;
import com.jamsirat.atmapi.exception.DataNotFoundException;
import com.jamsirat.atmapi.exception.RoleHasBeenAddedException;
import com.jamsirat.atmapi.exception.UnauthorizedGrantingAccessException;
import com.jamsirat.atmapi.model.auth.Role;
import com.jamsirat.atmapi.model.auth.User;
import com.jamsirat.atmapi.repository.IRoleRepository;
import com.jamsirat.atmapi.repository.IUserRepository;
import com.jamsirat.atmapi.service.IAuthorizationService;
import com.jamsirat.atmapi.statval.enumeration.EUserRole;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizationServiceImpl implements IAuthorizationService {

    private final IUserRepository userRepository;

    private final IRoleRepository roleRepository;

    @Override
    @Transactional
    public GrantRoleAccessResponse giveAccessToUser(Long userId, Long roleId, Principal principal) {
        User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User is not found","Please check your request id"));
        List<String> activeRoles = getRolesByLoggedInUser(principal);
        Role newRole = roleRepository.findById(roleId).orElseThrow(() -> new DataNotFoundException("Specified role does not exist","Please check your database"));
        if (user.getIsActive() == Boolean.TRUE && !activeRoles.contains(EUserRole.ADMIN.getName())) {
            throw new UnauthorizedGrantingAccessException("You are not allowed to grant access role","User role is not granted");
        }
        if (user.getRoles().stream().anyMatch(role -> role.getId().equals(roleId))) {
            throw new RoleHasBeenAddedException(String.format("Role %s has been added to user %s",newRole.getRoleName(),user.getFirstName()),"No need to add role");
        }
        user.getRoles().add(newRole);
        userRepository.save(user);
        return GrantRoleAccessResponse
                .builder()
                .message(String.format("new role has been granted to user %s", user.getFirstName()))
                .roleName(newRole.getRoleName())
                .build();

    }


    @Override
    public List<String> getRolesByLoggedInUser(Principal principal) {
        Set<Role> roles = getLoggedInUser(principal).getRoles();
        return roles.stream()
                .map(Role::getRoleName)
                .toList();
    }

    private User getLoggedInUser(Principal principal) {
        return userRepository.findByUserName(principal.getName()).get();
    }

}