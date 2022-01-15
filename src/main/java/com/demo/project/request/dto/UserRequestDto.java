package com.demo.project.request.dto;

import com.demo.project.Enum.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobileNo;
	private String emailId;
	private String addressLine1;
	private String addressLine2;
	private Gender gender;
}
