package com.demo.project.request.dto;

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
public class PaymentRequestDto {

	private String fromMobileNo;
	private String toMobileNo;
	private double amount;
}
