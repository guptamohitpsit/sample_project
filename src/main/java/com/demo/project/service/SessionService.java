package com.demo.project.service;

import com.demo.project.entity.UserSessionEntity;
import com.demo.project.response.dto.UserResponseDto;

public interface SessionService {

	UserSessionEntity createUserSession(UserResponseDto userProfileDto, String token);

	void removeUserSession(String mobileNo);

	UserSessionEntity getUserSessionByToken(String token);

	void updateUserSession(UserSessionEntity userSession);

}
