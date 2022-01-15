package com.demo.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.project.request.dto.PaymentRequestDto;
import com.demo.project.response.dto.ResponseDto;
import com.demo.project.service.PaymentService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/send")
	public ResponseDto<Boolean> transferMoney(@Valid @RequestBody PaymentRequestDto paymentRequestDto) {
		log.info("transfer money {}", paymentRequestDto);

		Boolean status = paymentService.transferMoney(paymentRequestDto);

		return status ? ResponseDto.success("Transaction success.", status) : ResponseDto.failure("Transaction failed");
	}
}
