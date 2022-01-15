package com.demo.project.service;

import java.util.List;

import com.demo.project.entity.UserEntity;
import com.demo.project.request.dto.UserRequestDto;
import com.demo.project.response.dto.UserResponseDto;

public interface UserService {

	UserResponseDto createUser(UserRequestDto userRequestDto);

	UserResponseDto updateUser(UserRequestDto userRequestDto);

	UserResponseDto getUserDetails(String mobileNo);

	boolean deActivateUser(String mobileNo);

	List<UserResponseDto> getAllUser();

	UserEntity getUserEntity(String fromMobileNo);

}
