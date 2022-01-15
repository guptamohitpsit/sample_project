package com.demo.project.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class AuthException extends RuntimeException {

	private static final long serialVersionUID = 2383761162199412703L;

	private final String message;

	private final String errorCode;

	public AuthException(String message) {
		super(message);
		this.message = message;
		this.errorCode = null;
	}

	public AuthException(String message, String errorCode) {
		super(message);
		this.message = message;
		this.errorCode = errorCode;
	}
}