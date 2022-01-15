package com.demo.project.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.project.Enum.PaymentStatus;
import com.demo.project.Enum.TransactionType;
import com.demo.project.adapters.WalletAdapters;
import com.demo.project.entity.TransactionEntity;
import com.demo.project.entity.UserEntity;
import com.demo.project.exception.NoRecordException;
import com.demo.project.repository.TransactionRepository;
import com.demo.project.repository.UserRepository;
import com.demo.project.request.dto.WalletRequestDto;
import com.demo.project.response.dto.WalletResponseDto;
import com.demo.project.service.WalletService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class WalletServiceImpl implements WalletService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Boolean addMoney(WalletRequestDto walletRequestDto) {

		UserEntity userEntity = userRepository.findByMobileNo(walletRequestDto.getMobileNo());

		if (Objects.isNull(userEntity)) {
			log.error("user not with mobile No {}", walletRequestDto.getMobileNo());
			throw new NoRecordException("user not with mobile No");
		}
		userEntity.setWallet(userEntity.getWallet()+walletRequestDto.getWallet());
		userEntity = userRepository.save(userEntity);
		
		TransactionEntity transaction=TransactionEntity.builder()
				.amount(walletRequestDto.getWallet()).mobileNo(walletRequestDto.getMobileNo())
				.paymentMode(walletRequestDto.getPaymentMode()).paymentStatus(PaymentStatus.SUCCESS)
				.transactionType(TransactionType.CREDIT)
				.build();
		transactionRepository.save(transaction);
		
		return true;
	}

	@Override
	public Double getWalletMoney(String mobileNo) {
		UserEntity userEntity = userRepository.findByMobileNo(mobileNo);

		if (Objects.isNull(userEntity)) {
			log.error("user not with mobile No {}", mobileNo);
			throw new NoRecordException("user not with mobile No");
		}

		return userEntity.getWallet();
	}

	@Override
	public List<WalletResponseDto> getWalletList() {
		List<UserEntity> users = userRepository.findByStatus(Boolean.TRUE);

		return WalletAdapters.getDtoList(users);
	}

}
