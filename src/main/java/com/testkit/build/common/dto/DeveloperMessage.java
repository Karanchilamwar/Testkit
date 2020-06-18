package com.testkit.build.common.dto;

import com.testkit.build.common.enums.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeveloperMessage {
	private String errorCode;
	private String message;
	private String developerMessage;

	public DeveloperMessage(ErrorCode errorCode) {
		this(Integer.toString(errorCode.getCode()), errorCode.getMessage(), null);

	}

	public DeveloperMessage(ErrorCode errorCode, String developerMessage) {
		this(Integer.toString(errorCode.getCode()), errorCode.getMessage(), developerMessage);

	}

}
