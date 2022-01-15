package com.demo.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.project.Enum.TransactionType;
import com.demo.project.adapters.TransactionAdapters;
import com.demo.project.entity.TransactionEntity;
import com.demo.project.repository.TransactionRepository;
import com.demo.project.response.dto.TransactionResponseDto;
import com.demo.project.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<TransactionResponseDto> getTransactionHistory(String mobileNo, TransactionType transactionType) {
		List<TransactionEntity> transactions = new ArrayList<>();
		if (Objects.isNull(transactionType)) {
			transactions = transactionRepository.findByMobileNo(mobileNo);
		} else {
			transactions = transactionRepository.findByMobileNoAndTransactionType(mobileNo, transactionType);
		}
		return TransactionAdapters.getDtoList(transactions);
	}

}
