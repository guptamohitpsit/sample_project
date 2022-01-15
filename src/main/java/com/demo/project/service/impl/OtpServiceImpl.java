package com.demo.project.service.impl;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.project.entity.OtpEntity;
import com.demo.project.entity.UserEntity;
import com.demo.project.exception.AuthException;
import com.demo.project.exception.NoRecordException;
import com.demo.project.repository.OtpRepository;
import com.demo.project.request.dto.OtpValidateRequestDto;
import com.demo.project.service.OtpService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OtpServiceImpl implements OtpService {

	@Autowired
	private OtpRepository otpRepository;

	@Override
	public void sendLoginOtp(UserEntity userEntity) {

		OtpEntity otpEntity = otpRepository.findByMobileNo(userEntity.getMobileNo());
        if(Objects.isNull(otpEntity)) {
        	otpEntity=OtpEntity.builder().mobileNo(userEntity.getMobileNo()) .build(); 
        }
		otpEntity.setOtp((int) ThreadLocalRandom.current().nextDouble(Math.pow(10, 4 - 1), Math.pow(10, 4)));
		otpEntity.setStatus(true);
		otpEntity.setValidateCount(0);
		otpEntity.setResendCount(0);
		otpRepository.save(otpEntity);

		log.info("Sending OTP: " + otpEntity.getOtp() + " for User: " + otpEntity.getMobileNo() + " for login");

	}

	@Override
	public void resendOtp(UserEntity userEntity) {
		OtpEntity otpEntity = otpRepository.findByMobileNo(userEntity.getMobileNo());
		
		if(otpEntity.getResendCount()==5) {
			log.error("reached resend maximum limit");
			throw new AuthException("Resend OTP can be used maximum 5 times.");
		}
		
		otpEntity.setOtp((int) ThreadLocalRandom.current().nextDouble(Math.pow(10, 4 - 1), Math.pow(10, 4)));
		otpEntity.setStatus(true);
		otpEntity.setResendCount(otpEntity.getResendCount() + 1);
		otpRepository.save(otpEntity);
	}

	@Override
	public void validateMobileOtp(OtpValidateRequestDto otpValidateRequestDto) {

		OtpEntity otpEntity = otpRepository.findByMobileNoAndStatus(otpValidateRequestDto.getMobileNo(), Boolean.TRUE);
		compareOTP(otpEntity, otpValidateRequestDto.getOtp());
		expiredOtp(otpEntity);
		
	}

	private void expiredOtp(OtpEntity otpEntity) {
		otpEntity.setStatus(false);
		otpRepository.save(otpEntity);
	}

	private void compareOTP(OtpEntity otpEntity, String otp) {
		if (otpEntity == null) {
			log.error("No OTP exists for mobile");
			throw new NoRecordException("No OTP exists for mobile");
		}
		if (!otpEntity.getOtp().toString().equals(otp)) {

			if (otpEntity.getValidateCount() >= 5) {
				log.error("Oops! Too many failed attempts. Please request for new OTP and try again.");
				throw new AuthException("Oops! Too many failed attempts. Please request for new OTP and try again.");
			} else {
				validateCountIncreate(otpEntity);
				throw new AuthException("Please Enter correct OTP.");
			}
		}

	}

	private void validateCountIncreate(OtpEntity otpEntity) {
		otpEntity.setValidateCount(otpEntity.getValidateCount() + 1);
		otpRepository.save(otpEntity);
		
	}

}
