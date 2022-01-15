package com.demo.project.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {
	FAILED("Failed"),
	PENDING("Pending"),
	SUCCESS("Success");
	
	String name;
	
}
