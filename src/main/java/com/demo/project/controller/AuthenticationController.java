package com.demo.project.controller;

import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.project.entity.UserSessionEntity;
import com.demo.project.request.dto.LoginRequestDto;
import com.demo.project.request.dto.OtpValidateRequestDto;
import com.demo.project.response.dto.ResponseDto;
import com.demo.project.response.dto.UserResponseDto;
import com.demo.project.service.AuthService;
import com.demo.project.service.SessionService;
import com.demo.project.utils.SecurityConstants;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class AuthenticationController {

	@Autowired
	private AuthService authService;

	@Autowired
	private SessionService sessionService;

	@PostMapping("login")
	public ResponseDto<Void> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {

		authService.login(loginRequestDto);

		return ResponseDto.success("OTP Sent for Login");
	}

	@PostMapping("resendOtp")
	public ResponseDto<Void> resendOtp(@RequestBody @Valid LoginRequestDto loginRequestDto) {

		authService.resendOtp(loginRequestDto);

		return ResponseDto.success("OTP Successfully Resent");
	}

	@PostMapping("validateOtp")
	public ResponseDto<UserResponseDto> validateOtp(@RequestBody @Valid OtpValidateRequestDto otpValidateRequestDto,
			HttpServletRequest request, HttpServletResponse response) {

		UserResponseDto userProfileDto = authService.validateOtp(otpValidateRequestDto);

		log.info("OTP Successfully Validated for User: " + userProfileDto.getId() + ". Creating User Session now");

		String token = UUID.randomUUID().toString();

		UserSessionEntity userSessionEntity = sessionService.createUserSession(userProfileDto, token);

		if (Objects.nonNull(userSessionEntity)) {
			addTokenToResponse(request, response, token);
			return ResponseDto.success("User Login Successfull", userProfileDto);
		}

		return ResponseDto.failure("Failed to create user session");
	}

	private void addTokenToResponse(HttpServletRequest request, HttpServletResponse response, String token) {

		if (Objects.nonNull(token)) {
			Cookie cookie = new Cookie(SecurityConstants.TOKEN_HEADER_NAME, token);
			response.addCookie(cookie);
		}
	}

	@GetMapping("logout/{mobileNo}")
	public ResponseDto<Void> logout(@PathVariable("mobileNo") String mobileNo, HttpServletRequest request,
			HttpServletResponse response) {

		String userId = request.getParameter(SecurityConstants.MobileNo);

		log.info("Logout requested for user: " + userId);

		sessionService.removeUserSession(mobileNo);

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {

				if (SecurityConstants.TOKEN_HEADER_NAME.equals(cookie.getName())) {
					cookie.setMaxAge(0);
					cookie.setValue(null);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}

		return ResponseDto.success("Successfully Logged Out");
	}
}
