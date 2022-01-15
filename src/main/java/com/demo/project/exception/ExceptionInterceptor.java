package com.demo.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.project.response.dto.ResponseDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
public class ExceptionInterceptor {

	@ExceptionHandler(NoRecordException.class)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public <T> ResponseDto<T> handleNoRecordException(NoRecordException e) {

		log.error("Got NoRecordException for exceptionId: {} with Message: {}", e);

		return ResponseDto.failure(e.getMessage());
	}

	@ExceptionHandler(RecordExistsException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)

	public <T> ResponseDto<T> handleRecordExistsException(RecordExistsException e) {

		log.error("Got RecordExistsException for exceptionId: {} and message: {}", e.getMessage());

		return ResponseDto.failure(e.getMessage());
	}

	@ExceptionHandler(AuthException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public <T> ResponseDto<T> handleAuthException(AuthException e) {

		log.error("Got AuthException for exceptionId: {}", e);

		return ResponseDto.failure(e.getMessage());
	}

	@ExceptionHandler(ApiValidationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public <T> ResponseDto<T> handleApiValidationException(ApiValidationException e) {

		log.error("Got ApiValidationException Message: {}", e.getMessage());

		return ResponseDto.failure(e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public <T> ResponseDto<T> handleException(Exception ex) {

		log.error("Got un-handled exception for exceptionId: {}", ex);

		return ResponseDto.failure(ex.getMessage());
	}
}