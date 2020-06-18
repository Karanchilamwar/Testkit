package com.testkit.build.common.exception;

import com.testkit.build.common.dto.ErrorMessage;

import lombok.Getter;

@Getter
public abstract class AbstractException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	protected final ErrorMessage errorMessage;

	public AbstractException(ErrorMessage errorMessage) {
		super(errorMessage.getMessage());
		this.errorMessage = errorMessage;
	}

	public AbstractException(ErrorMessage errorMessage, Throwable throwable) {
		super(throwable);
		this.errorMessage = errorMessage;
	}

	public ErrorMessage getErrorMessage() {
		return this.errorMessage;
	}
}
