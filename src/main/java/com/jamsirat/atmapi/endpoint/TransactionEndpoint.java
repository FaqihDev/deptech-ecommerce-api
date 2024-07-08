package com.jamsirat.atmapi.endpoint;


import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.order.TransactionRequestDto;
import com.jamsirat.atmapi.exception.OutOfStockException;
import com.jamsirat.atmapi.service.ITransactionHistoryService;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = IApplicationConstant.ContextPath.ORDER,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionEndpoint {


    private final ITransactionHistoryService iTransactionHistoryService;

    @PostMapping(IApplicationConstant.Path.Order.PERFORM_TRANSACTION)
    public ResponseEntity<?> doTransaction(HttpServletRequest httpServletRequest,@RequestBody TransactionRequestDto request) {
        try {
            HttpResponse<?> response = iTransactionHistoryService.processTransaction(httpServletRequest,request);
            if (Objects.nonNull(response) && Objects.nonNull(response.getData())) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(HttpResponse.noContent());
        } catch (OutOfStockException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(HttpResponse.buildHttpResponse(e.getExceptionMessage(), e.getDeveloperMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @GetMapping(IApplicationConstant.Path.Order.TRANSACTION_HISTORIES)
    public ResponseEntity<?> fetchTransactionHistory () {
        try {
            HttpResponse<?> response = iTransactionHistoryService.listTransactionHistory();
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
