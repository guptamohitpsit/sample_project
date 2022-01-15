package com.demo.project.service;

import com.demo.project.request.dto.PaymentRequestDto;

public interface PaymentService {

	Boolean transferMoney(PaymentRequestDto paymentRequestDto);

}
