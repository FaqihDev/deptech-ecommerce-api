package com.jamsirat.atmapi.service;

import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.order.TransactionRequestDto;
import jakarta.servlet.http.HttpServletRequest;

public interface ITransactionHistoryService {

     HttpResponse<?> processTransaction(HttpServletRequest httpServletRequest,TransactionRequestDto request);

     HttpResponse<?> listTransactionHistory();

}
