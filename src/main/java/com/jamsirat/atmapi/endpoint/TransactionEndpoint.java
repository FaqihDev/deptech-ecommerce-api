package com.jamsirat.atmapi.endpoint;


import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.order.TransactionRequestDto;
import com.jamsirat.atmapi.exception.OutOfStockException;
import com.jamsirat.atmapi.service.ITransactionHistoryService;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = IApplicationConstant.ContextPath.ORDER,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionEndpoint {


    private final ITransactionHistoryService iTransactionHistoryService;

    @PostMapping(IApplicationConstant.Path.Order.PERFORM_TRANSACTION)
    public ResponseEntity<?> doTransaction(HttpServletRequest httpServletRequest, @RequestBody TransactionRequestDto request) {
        try {
            HttpResponse<?> response = iTransactionHistoryService.processTransaction(httpServletRequest, request);
            return HttpResponse.okOrNoContent(response);
        } catch (OutOfStockException e) {
           return ResponseEntity.badRequest().body(HttpResponse.outOfStock());
        }
    }

    @GetMapping(IApplicationConstant.Path.Order.TRANSACTION_HISTORIES)
    public ResponseEntity<?> fetchTransactionHistory() {
        HttpResponse<?> response = iTransactionHistoryService.listTransactionHistory();
        return HttpResponse.okOrNoContent(response);
    }
}
