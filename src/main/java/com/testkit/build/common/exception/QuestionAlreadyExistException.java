package com.testkit.build.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.testkit.build.common.dto.ErrorMessage;

import lombok.Getter;

@Getter
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class QuestionAlreadyExistException extends AbstractException {
	private static final long serialVersionUID = 6277511269832317527L;

	public QuestionAlreadyExistException(ErrorMessage errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

	public QuestionAlreadyExistException(ErrorMessage errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
		// TODO Auto-generated constructor stub
	}
}
