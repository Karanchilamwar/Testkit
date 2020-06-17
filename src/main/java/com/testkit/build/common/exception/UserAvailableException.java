package com.testkit.build.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.testkit.build.common.dto.ErrorMessage;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserAvailableException extends AbstractException {
	private static final long serialVersionUID = 6277511269832317527L;

	public UserAvailableException(ErrorMessage errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

}
