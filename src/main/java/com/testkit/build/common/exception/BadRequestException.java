package com.testkit.build.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.testkit.build.common.dto.ErrorMessage;

import lombok.Getter;

@Getter
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends AbstractException {
	private static final long serialVersionUID = 6277511269832317527L;

	public BadRequestException(ErrorMessage errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

	public BadRequestException(ErrorMessage errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
		// TODO Auto-generated constructor stub
	}
}
