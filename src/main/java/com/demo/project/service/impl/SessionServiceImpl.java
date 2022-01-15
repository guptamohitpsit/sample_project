package com.demo.project.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.project.entity.UserSessionEntity;
import com.demo.project.exception.NoRecordException;
import com.demo.project.repository.UserSessionRepository;
import com.demo.project.response.dto.UserResponseDto;
import com.demo.project.service.SessionService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SessionServiceImpl implements SessionService {

	@Autowired
	private UserSessionRepository userSessionRepository;

	@Override
	public UserSessionEntity createUserSession(UserResponseDto userResponseDto, String token) {

		UserSessionEntity userSessionEntity =userSessionRepository.findByMobileNo(userResponseDto.getMobileNo());
		if(Objects.isNull(userSessionEntity)) {
			userSessionEntity=UserSessionEntity.builder().mobileNo(userResponseDto.getMobileNo()) .build();
		}
		log.info("Creating Session for User: " + userResponseDto.getMobileNo());
		userSessionEntity.setStatus(true);
		userSessionEntity.setToken(token);

		userSessionEntity = userSessionRepository.saveAndFlush(userSessionEntity);

		log.info("Created Session: " + userSessionEntity.getToken() + " for User: " + userResponseDto.getMobileNo());

		return userSessionEntity;
	}

	@Override
	public void removeUserSession(String mobileNo) {
		UserSessionEntity userSessionEntity =userSessionRepository.findByMobileNo(mobileNo);

		if (Objects.isNull(userSessionEntity)) {
			log.error("No User Session Found!! Please Login!!");
			throw new NoRecordException("No User Session Found!! Please Login!!");
		}

		log.info("Invalidating User Session: " + userSessionEntity.getMobileNo());

		userSessionEntity.setStatus(false);
		userSessionRepository.saveAndFlush(userSessionEntity);

	}

	@Override
	public UserSessionEntity getUserSessionByToken(String token) {
		
		UserSessionEntity userSessionEntity =userSessionRepository.findByToken(token);

		if (Objects.isNull(userSessionEntity)) {
			log.error("No User Session Found!! Please Login!!");
		}
		return userSessionEntity;
	}

	@Override
	public void updateUserSession(UserSessionEntity userSession) {
		userSessionRepository.save(userSession);
		
	}

}
