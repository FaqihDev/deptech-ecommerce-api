package com.jamsirat.atmapi.base;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class APIResponseBuilder {

    private static <T> IResultDTO<T> ok (ResponseDataDTO responseDataDTO, T result, Map<String, String> metadata) {
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setResult(result);
        resultDTO.setResponseData(responseDataDTO);
        resultDTO.setMetadata(metadata);

        return resultDTO;
    }

    public static <T> IResultDTO<T> ok (int statusCode, T result, String message, Map<String, String> metadata) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setResponseMsg(message);
        responseDataDTO.setResponseCode(statusCode);

        return ok(responseDataDTO,result,metadata);
    }

    public static <T> IResultDTO<T> ok (HttpStatus status, T result, String message, Map<String, String> metadata) {
        ResponseDataDTO responseDataDTO =  new ResponseDataDTO();
        responseDataDTO.setResponseCode(status);
        responseDataDTO.setResponseMsg(message);

        return ok(responseDataDTO,result,metadata);
    }

    public static <T> IResultDTO<T> ok (T result, String message) {
        return ok(HttpStatus.OK,result,message, null);
    }

    public static <T> IResultDTO<T> ok (T result, Map<String,String> metadata) {
        return ok(HttpStatus.OK,result,"Success", metadata);
    }

    public static <T> ResultDTO<List<T>> buildBaseListResponseData(HttpStatus status, String message, List<T> result) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setResponseCode(status);
        responseDataDTO.setResponseMsg(message);

        ResultDTO<List<T>> resultDTO = new ResultDTO<>();
        resultDTO.setResponseData(responseDataDTO);
        resultDTO.setResult(result);

        return resultDTO;
    }

    public static <T> IResultDTO<T> noContent(T result, String message) {
        return ok(HttpStatus.NO_CONTENT,result,message,null);
    }

    private static <T> IResultDTO<T> prepareErrorDTO(
            HttpStatus status,
            String errMessage,
            T result,
            Exception exception,
            String message,
            HttpServletRequest request
    ) {
        return prepareErrorDTO(
                status,
                errMessage,
                result,
                exception,
                message,
                request != null ? request.getMethod() + " " + request.getRequestURI() : null,
                null);
    }




    private static <T> IResultDTO<T> prepareErrorDTO(HttpStatus status,
                                                     String errMsg,
                                                     T result,
                                                     Exception exception,
                                                     String message,
                                                     String path,
                                                     Map<String, String> metadata) {

        BaseResultDTO<T,ResponseAPIErrorDTO> resultDTO = new BaseResultDTO<>();
        resultDTO.setResult(result);
        resultDTO.setResponseData(
                new ResponseAPIErrorDTO(
                        new Date(),
                        status,
                        Objects.isNull(exception) ? null : exception.getClass()
                                                                    .getCanonicalName() + " :: " + exception.getMessage(),
                        Strings.isBlank(message) && Objects.nonNull(exception) ? exception.getMessage() : message,
                        path,
                        errMsg
                        )
        );

        if (Objects.nonNull(metadata)) {
            resultDTO.setMetadata(metadata);
        }
        return resultDTO;
    }

    public static <T> IResultDTO<T> notFound(
            T result, Exception exception, String message, HttpServletRequest request
    ) {
        return prepareErrorDTO(HttpStatus.NOT_FOUND,"Not Found",result,exception,message,request);
    }

    public static <T> IResultDTO<T> conflict(T result, Exception e,
                                             String message,
                                             HttpServletRequest request) {
        return prepareErrorDTO(HttpStatus.CONFLICT,"Conflict",result,e,message,request);
    }

    public static <T> IResultDTO<T> generic (HttpStatus status,
                                             Exception e,
                                             String message,
                                             HttpServletRequest request) {
        return prepareErrorDTO(status,status.getReasonPhrase(),null,e,message,request);
    }

    public static <T> IResultDTO<T> internalServerError(
            T result,
            Exception exception,
            String message,
            HttpServletRequest request
    ) {
        return prepareErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                result,
                exception,
                message,
                request
        );
    }

    public static <T> IResultDTO<T> badRequest(
            T result,
            Exception exception,
            String message,
            HttpServletRequest request
    ) {
        return prepareErrorDTO(
                HttpStatus.BAD_REQUEST,
                "Bad Request",
                result,
                exception,
                message,
                request
        );
    }

    public static <T> IResultDTO<T> unsupportedOperation(
            T result, Exception exception, String message, HttpServletRequest request
    ) {
        return prepareErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY,
                "Unsupported Operation",
                result,
                exception,
                message,
                request
        );

    }








}