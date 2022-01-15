package com.demo.project.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.project.adapters.UserAdapters;
import com.demo.project.entity.UserEntity;
import com.demo.project.exception.ApiValidationException;
import com.demo.project.exception.NoRecordException;
import com.demo.project.exception.RecordExistsException;
import com.demo.project.repository.UserRepository;
import com.demo.project.request.dto.UserRequestDto;
import com.demo.project.response.dto.UserResponseDto;
import com.demo.project.service.UserService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserResponseDto createUser(UserRequestDto userRequestDto) {

		UserEntity user = userRepository.findByMobileNo(userRequestDto.getMobileNo());

		if (Objects.nonNull(user)) {
			log.error("user already exits with mobile No {}", userRequestDto.getMobileNo());
			throw new RecordExistsException("user not found with mobile No");
		}
		user=UserEntity.builder().build();
		BeanUtils.copyProperties(userRequestDto, user);
		user = userRepository.save(user);

		return UserAdapters.getDto(user);
	}

	@Override
	public UserResponseDto updateUser(UserRequestDto userRequestDto) {

		UserEntity user = getUserEntity(userRequestDto.getMobileNo());

		BeanUtils.copyProperties(userRequestDto, user);

		return UserAdapters.getDto(userRepository.save(user));
	}

	@Override
	public UserResponseDto getUserDetails(String mobileNo) {

		UserEntity user = getUserEntity(mobileNo);
		return UserAdapters.getDto(user);
	}
	
	@Override
	public UserEntity getUserEntity(String mobileNo) {
		UserEntity user = userRepository.findByMobileNo(mobileNo);

		if (Objects.isNull(user)) {
			log.error("user not found with mobile No {}", mobileNo);
			throw new ApiValidationException("user not found with mobile No");
		}
		return user;
	}

	@Override
	public boolean deActivateUser(String mobileNo) {
		UserEntity user = getUserEntity(mobileNo);
		user.setStatus(false);
		userRepository.save(user);
		return true;
	}

	@Override
	public List<UserResponseDto> getAllUser() {
		List<UserEntity> users = userRepository.findByStatus(Boolean.TRUE);

		return UserAdapters.getDtoList(users);
	}

}
