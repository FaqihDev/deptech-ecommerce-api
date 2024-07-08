package com.jamsirat.atmapi.endpoint;

import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.user.RequestUpdateUserDto;
import com.jamsirat.atmapi.dto.response.user.ResponseDetailUserDto;
import com.jamsirat.atmapi.service.IUserService;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = IApplicationConstant.ContextPath.USER,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserEndpoint {

    private final IUserService userService;


    @GetMapping(IApplicationConstant.Path.User.LIST_USERS)
    public ResponseEntity<?> fetchUsers() {
        try {
            HttpResponse<List<ResponseDetailUserDto>> response = userService.getListUsers();
            if (Objects.nonNull(response) && Objects.nonNull(response.getData()) && !response.getData().isEmpty()) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(HttpResponse.noContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @GetMapping(IApplicationConstant.Path.User.USER_DETAIL)
    public ResponseEntity<?> detailUser(HttpServletRequest request) {
        try {
            HttpResponse<?> response = userService.getDetailUsers(request);
            if (Objects.nonNull(response) && Objects.nonNull(response.getData())) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(HttpResponse.noContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @PutMapping(IApplicationConstant.Path.User.UPDATE_USER)
    public ResponseEntity<?> update(@RequestBody RequestUpdateUserDto request) {
        try {
            HttpResponse<?> response = userService.updateUsers(request);
            if (Objects.nonNull(response) && Objects.nonNull(response.getData())) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(HttpResponse.noContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @DeleteMapping(IApplicationConstant.Path.User.DELETE_USER)
    public ResponseEntity<?> delete(@PathVariable Long userId) {
        try {
            HttpResponse<?> response = userService.deleteUsers(userId);
            if (Objects.nonNull(response) && Objects.nonNull(response.getData())) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(HttpResponse.noContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

}
