package com.demo.project.response.dto;

import com.demo.project.request.dto.UserRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto extends UserRequestDto {

	private int id;
	private Double wallet;

}
