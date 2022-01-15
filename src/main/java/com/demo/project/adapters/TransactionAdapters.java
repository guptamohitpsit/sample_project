package com.demo.project.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.demo.project.entity.TransactionEntity;
import com.demo.project.response.dto.TransactionResponseDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TransactionAdapters {

	public static TransactionResponseDto getDto(TransactionEntity transactionEntity) {

		if (Objects.isNull(transactionEntity)) {
			return null;
		}
		TransactionResponseDto transactionResponseDto = TransactionResponseDto.builder().build();
		BeanUtils.copyProperties(transactionEntity, transactionResponseDto);
		return transactionResponseDto;

	}

	public static List<TransactionResponseDto> getDtoList(List<TransactionEntity> transactions) {

		List<TransactionResponseDto> transactionList = new ArrayList<>();
		for (TransactionEntity transaction : transactions) {
			transactionList.add(getDto(transaction));
		}
		return transactionList;
	}
}
