package com.demo.project.response.dto;

import com.demo.project.Enum.PaymentMode;
import com.demo.project.Enum.PaymentStatus;
import com.demo.project.Enum.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {
	private double amount;
	private String mobileNo;
	private PaymentMode paymentMode;
	private PaymentStatus paymentStatus;
	private TransactionType transactionType;
}
