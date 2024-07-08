package com.jamsirat.atmapi.dto.base;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class HttpResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -8781718059336021092L;

    private String timeStamp;
    private String developerMessage;
    private String message;
    private HttpStatus status;
    private int statusCode;
    private T data;

    public static <T> HttpResponse<T> build(String developerMessage, String message, HttpStatus status, T data) {
        return HttpResponse.<T>builder()
                .timeStamp(LocalDateTime.now().toString())
                .developerMessage(developerMessage)
                .message(message)
                .status(status)
                .statusCode(status.value())
                .data(data)
                .build();
    }

    public static <T> HttpResponse<T> noContent() {
        return build("Data is empty", "no content", HttpStatus.NO_CONTENT, null);
    }

    public static <T> HttpResponse<T> unauthorized() {
        return build("Unauthorized", "Access Denied", HttpStatus.UNAUTHORIZED, null);
    }

    public static <T> HttpResponse<T> InvalidatedToken() {
        return build("Token is Expired or Invalid", "Please do login!", HttpStatus.UNAUTHORIZED, null);
    }

    public static <T> HttpResponse<T> outOfStock() {
        return build("Sorry we are out of stock","Please contact your admin!",HttpStatus.BAD_REQUEST,null);
    }

    public static <T> ResponseEntity<HttpResponse<T>> okOrNoContent(HttpResponse<T> response) {
        return ResponseEntity.ok(response != null && response.getData() != null ? response : noContent());
    }
}
