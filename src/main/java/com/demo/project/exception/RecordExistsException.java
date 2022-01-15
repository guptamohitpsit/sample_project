package com.demo.project.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class RecordExistsException extends RuntimeException {

	private static final long serialVersionUID = -3368655266237942363L;
	private final String message;
	public RecordExistsException(String message) {
		super(message);
		this.message=message;
	}

	public RecordExistsException(Throwable cause) {
		super(cause);
		this.message=null;
	}

	public RecordExistsException(String message, Throwable cause) {
		super(message, cause);
		this.message=message;
	}

}