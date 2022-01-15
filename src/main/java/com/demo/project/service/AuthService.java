package com.demo.project.service;

import com.demo.project.request.dto.LoginRequestDto;
import com.demo.project.request.dto.OtpValidateRequestDto;
import com.demo.project.response.dto.UserResponseDto;

public interface AuthService {

	void login(LoginRequestDto loginRequestDto);

	void resendOtp(LoginRequestDto loginRequestDto);

	UserResponseDto validateOtp(OtpValidateRequestDto otpValidateRequestDto);

}
