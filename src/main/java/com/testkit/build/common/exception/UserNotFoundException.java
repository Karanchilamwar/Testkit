package com.testkit.build.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.testkit.build.common.dto.ErrorMessage;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends AbstractException {
	private static final long serialVersionUID = 6277511269832317527L;

	public UserNotFoundException(ErrorMessage errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

}
