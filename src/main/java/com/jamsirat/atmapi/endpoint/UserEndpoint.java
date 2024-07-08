package com.jamsirat.atmapi.endpoint;

import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.user.RequestUpdateUserDto;
import com.jamsirat.atmapi.dto.response.user.ResponseDetailUserDto;
import com.jamsirat.atmapi.service.IUserService;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = IApplicationConstant.ContextPath.USER,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserEndpoint {

    private final IUserService userService;


    @GetMapping(IApplicationConstant.Path.User.LIST_USERS)
    public ResponseEntity<?> fetchUsers() {
        HttpResponse<List<ResponseDetailUserDto>> response = userService.getListUsers();
        return HttpResponse.okOrNoContent(response);
    }

    @GetMapping(IApplicationConstant.Path.User.USER_DETAIL)
    public ResponseEntity<?> detailUser(HttpServletRequest request) {
        HttpResponse<?> response = userService.getDetailUsers(request);
        return HttpResponse.okOrNoContent(response);
    }

    @PutMapping(IApplicationConstant.Path.User.UPDATE_USER)
    public ResponseEntity<?> update(@RequestBody RequestUpdateUserDto request) {
        HttpResponse<?> response = userService.updateUsers(request);
        return HttpResponse.okOrNoContent(response);
    }

    @DeleteMapping(IApplicationConstant.Path.User.DELETE_USER)
    public ResponseEntity<?> delete(@PathVariable Long userId) {
        HttpResponse<?> response = userService.deleteUsers(userId);
        return HttpResponse.okOrNoContent(response);
    }

}
