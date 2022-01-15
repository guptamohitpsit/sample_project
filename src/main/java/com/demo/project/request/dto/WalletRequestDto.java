package com.demo.project.request.dto;

import com.demo.project.Enum.PaymentMode;

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
public class WalletRequestDto {

	private String MobileNo;
	private Double wallet;
	private PaymentMode paymentMode;

	
	
}
