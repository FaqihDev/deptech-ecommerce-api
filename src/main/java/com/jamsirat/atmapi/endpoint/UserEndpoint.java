package com.jamsirat.atmapi.endpoint;

import com.jamsirat.atmapi.dto.request.user.RequestUpdateUserDto;
import com.jamsirat.atmapi.service.IUserService;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = IApplicationConstant.ContextPath.USER,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserEndpoint {

    private final IUserService userService;


    @GetMapping(IApplicationConstant.Path.User.LIST_USERS)
    public ResponseEntity<?> fetchUsers() {
        return ResponseEntity.ok(userService.getListUsers());
    }

    @GetMapping(IApplicationConstant.Path.User.USER_DETAIL)
    public ResponseEntity<?> detailUser(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getDetailUsers(request));
    }

    @PutMapping(IApplicationConstant.Path.User.UPDATE_USER)
    public ResponseEntity<?> update(@RequestBody RequestUpdateUserDto request) {
        return ResponseEntity.ok(userService.updateUsers(request));
    }

    @DeleteMapping(IApplicationConstant.Path.User.DELETE_USER)
    public ResponseEntity<?> update(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.deleteUsers(userId));
    }

}
