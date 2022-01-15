package com.demo.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.project.Enum.PaymentMode;
import com.demo.project.Enum.PaymentStatus;
import com.demo.project.Enum.TransactionType;
import com.demo.project.entity.TransactionEntity;
import com.demo.project.entity.UserEntity;
import com.demo.project.exception.ApiValidationException;
import com.demo.project.repository.TransactionRepository;
import com.demo.project.repository.UserRepository;
import com.demo.project.request.dto.PaymentRequestDto;
import com.demo.project.service.PaymentService;
import com.demo.project.service.UserService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Boolean transferMoney(PaymentRequestDto paymentRequestDto) {

		UserEntity senderData = userService.getUserEntity(paymentRequestDto.getFromMobileNo());
		UserEntity recieverData = userService.getUserEntity(paymentRequestDto.getToMobileNo());

		if (senderData.getWallet() < paymentRequestDto.getAmount()) {
			log.error("In sufficient amount is wallet");
			throw new ApiValidationException("Transaction fails!! In sufficient amount is wallet");
		}
		creditMoney(recieverData, paymentRequestDto);
		debitedMoney(senderData, paymentRequestDto);

		return true;
	}

	private void debitedMoney(UserEntity senderData, PaymentRequestDto paymentRequestDto) {
		senderData.setWallet(senderData.getWallet() - paymentRequestDto.getAmount());

		userRepository.save(senderData);
		TransactionEntity transaction = TransactionEntity.builder().amount(paymentRequestDto.getAmount())
				.mobileNo(paymentRequestDto.getFromMobileNo()).paymentMode(PaymentMode.TRANSFER)
				.paymentStatus(PaymentStatus.SUCCESS).transactionType(TransactionType.DEBIT).build();
		transactionRepository.save(transaction);

	}

	private void creditMoney(UserEntity recieverData, PaymentRequestDto paymentRequestDto) {
		recieverData.setWallet(recieverData.getWallet() + paymentRequestDto.getAmount());
		userRepository.save(recieverData);
		TransactionEntity transaction = TransactionEntity.builder().amount(paymentRequestDto.getAmount())
				.mobileNo(paymentRequestDto.getToMobileNo()).paymentMode(PaymentMode.RECIEVED)
				.paymentStatus(PaymentStatus.SUCCESS).transactionType(TransactionType.CREDIT).build();
		transactionRepository.save(transaction);
	}
}
