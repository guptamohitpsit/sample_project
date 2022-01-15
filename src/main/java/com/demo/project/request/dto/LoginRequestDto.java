package com.demo.project.request.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
public class LoginRequestDto {

	@NotBlank(message = "Mobile Number is Mandatory for Login")
	@Size(min = 10,max = 10,message = "Mobile Number is inCorrect")
	private String mobileNo;
}
