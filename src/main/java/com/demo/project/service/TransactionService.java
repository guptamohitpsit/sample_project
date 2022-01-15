package com.demo.project.service;

import java.util.List;

import com.demo.project.Enum.TransactionType;
import com.demo.project.response.dto.TransactionResponseDto;

public interface TransactionService {

	List<TransactionResponseDto> getTransactionHistory(String mobileNo, TransactionType transactionType);

}
