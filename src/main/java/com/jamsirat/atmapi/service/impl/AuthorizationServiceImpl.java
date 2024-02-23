package com.jamsirat.atmapi.service.impl;

import com.jamsirat.atmapi.dto.response.GrantRoleAccessResponse;
import com.jamsirat.atmapi.exception.DataNotFoundException;
import com.jamsirat.atmapi.model.Role;
import com.jamsirat.atmapi.model.User;
import com.jamsirat.atmapi.repository.IRoleRepository;
import com.jamsirat.atmapi.repository.IUserRepository;
import com.jamsirat.atmapi.service.IAuthorizationService;
import com.jamsirat.atmapi.statval.enumeration.EAccessType;
import com.jamsirat.atmapi.statval.enumeration.EUserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizationServiceImpl implements IAuthorizationService {

    private final IUserRepository userRepository;

    private final IRoleRepository roleRepository;

    @Override
    public GrantRoleAccessResponse giveAccessToUser(Long userId, Long roleId, Principal principal) {
        User user = userRepository.findById(userId).get();
        List<String> activeRoles = getRolesByLoggedInUser(principal);
        Set<Role> roles = userRepository.findRolesById(user.getId());
        Role newRole = roleRepository.findById(roleId).orElseThrow(() -> new DataNotFoundException(404,"Specified role does not exist"));
        if (user.getIsActive() == Boolean.TRUE && activeRoles.contains(newRole.getRoleName())) {
                roles.add(newRole);
                roleRepository.saveAll(roles);
                user.setRoles(roles);
                userRepository.save(user);
        }
        return GrantRoleAccessResponse
                .builder()
                .message(String.format("new role has been granted to user %d", user.getFirstName()))
                .roleName(newRole.getRoleName())
                .build();
    }


    @Override
    public List<String> getRolesByLoggedInUser(Principal principal) {
        Set<Role> roles = getLoggedInUser(principal).getRoles();
        List<String> rolesName = roles.stream()
                .map(Role::getRoleName)
                .toList();
        for (String role : rolesName) {
            if (role.equals(EUserRole.ADMIN)) {
                return Arrays.stream(new String[]{EAccessType.ADMIN_ACCESS.getName()}).collect(Collectors.toList());
            }
            if (role.equals(EUserRole.PRINCIPLE)) {
                return Arrays.stream(new String[]{EAccessType.PRINCIPLE_ACCESS.getName()}).collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }

    private User getLoggedInUser(Principal principal) {
        return userRepository.findByUserName(principal.getName()).get();
    }

}