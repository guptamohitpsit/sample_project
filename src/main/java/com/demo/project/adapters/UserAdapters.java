package com.demo.project.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.demo.project.entity.UserEntity;
import com.demo.project.response.dto.UserResponseDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserAdapters {

	public static UserResponseDto getDto(UserEntity userEntity) {

		if (Objects.isNull(userEntity)) {
			return null;
		}
		UserResponseDto userResponseDto = UserResponseDto.builder().build();
		BeanUtils.copyProperties(userEntity, userResponseDto);
		return userResponseDto;
	}

	public static List<UserResponseDto> getDtoList(List<UserEntity> users) {
		List<UserResponseDto> userList = new ArrayList<>();
		for (UserEntity user : users) {
			userList.add(getDto(user));
		}

		return userList;
	}
}
