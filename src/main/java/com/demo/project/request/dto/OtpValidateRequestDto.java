package com.demo.project.request.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class OtpValidateRequestDto extends LoginRequestDto {
	@NotBlank(message = "OTP cannot be blank")
	@Size(min = 4, max = 6, message = "OTP must be of 4-6 charaters")
	private String otp;
}
