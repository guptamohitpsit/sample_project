package com.demo.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.project.Enum.TransactionType;
import com.demo.project.response.dto.ResponseDto;
import com.demo.project.response.dto.TransactionResponseDto;
import com.demo.project.service.TransactionService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/get/{mobileNo}")
	public ResponseDto<List<TransactionResponseDto>> getWalletMoney(@PathVariable("mobileNo") String mobileNo,
			@RequestParam(name = "transactionType", required = false) TransactionType transactionType) {

		log.info("get user wallet Information {} ", mobileNo);

		List<TransactionResponseDto> transactionResponseDtos = transactionService.getTransactionHistory(mobileNo,
				transactionType);

		return ResponseDto.success("User transaction Details", transactionResponseDtos);
	}
}
