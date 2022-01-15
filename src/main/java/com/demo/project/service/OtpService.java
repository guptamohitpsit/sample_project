package com.demo.project.service;

import com.demo.project.entity.UserEntity;
import com.demo.project.request.dto.OtpValidateRequestDto;

public interface OtpService {

	void sendLoginOtp(UserEntity userEntity);

	void resendOtp(UserEntity userEntity);

	void validateMobileOtp(OtpValidateRequestDto otpValidateRequestDto);

}
