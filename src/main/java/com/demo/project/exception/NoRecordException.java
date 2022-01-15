package com.demo.project.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class NoRecordException extends RuntimeException {

	private static final long serialVersionUID = -3368655266237942363L;

	private final String message;

	private final String errorCode;

	public NoRecordException(String message) {
		super(message);
		this.message = message;
		this.errorCode = null;
	}

	public NoRecordException(String message, String errorCode) {
		super(message);
		this.message = message;
		this.errorCode = errorCode;
	}

}