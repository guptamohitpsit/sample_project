package com.demo.project.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

	MALE("Male"),
	FEMALE("Female"),
	OTHERS("Others");

	private String genderName;
	
}