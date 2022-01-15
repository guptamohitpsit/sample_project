package com.demo.project.service.impl;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.project.adapters.UserAdapters;
import com.demo.project.entity.UserEntity;
import com.demo.project.exception.AuthException;
import com.demo.project.repository.UserRepository;
import com.demo.project.request.dto.LoginRequestDto;
import com.demo.project.request.dto.OtpValidateRequestDto;
import com.demo.project.response.dto.UserResponseDto;
import com.demo.project.service.AuthService;
import com.demo.project.service.OtpService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OtpService otpService;
	
	@Override
	public void login(LoginRequestDto loginRequestDto) {
		
		UserEntity userEntity = getActiveUser(loginRequestDto);

		otpService.sendLoginOtp(userEntity);

		log.info("OTP sent for User: " + userEntity.getMobileNo() + " for Login");
		
	}

	private UserEntity getActiveUser(LoginRequestDto loginRequestDto) {
		
		UserEntity userEntity = userRepository.findByMobileNo(loginRequestDto.getMobileNo());

		if (Objects.isNull(userEntity)) {
			log.error("user already exits with mobile No {}", loginRequestDto.getMobileNo());
			throw new AuthException("No user exists with this number");
		}
		if (!userEntity.isStatus()) {
			log.error("user is deactivated {}", loginRequestDto.getMobileNo());
			throw new AuthException("user is deactivated with mobile No");
		}
		return userEntity;
	}

	@Override
	public void resendOtp(LoginRequestDto loginRequestDto) {
		UserEntity userEntity = getActiveUser(loginRequestDto);
		otpService.resendOtp(userEntity);
		
	}

	@Override
	public UserResponseDto validateOtp(@Valid OtpValidateRequestDto otpValidateRequestDto) {
		UserEntity userEntity = getActiveUser(otpValidateRequestDto);
		otpService.validateMobileOtp(otpValidateRequestDto);
		
		return UserAdapters.getDto(userEntity);
	}

}
