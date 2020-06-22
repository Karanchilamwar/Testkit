package com.testkit.build.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.testkit.build.common.dto.ErrorMessage;

import lombok.Getter;

@Getter
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends AbstractException {
	private static final long serialVersionUID = 6277511269832317527L;

	public NotFoundException(ErrorMessage errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

	public NotFoundException(ErrorMessage errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
		// TODO Auto-generated constructor stub
	}
}
