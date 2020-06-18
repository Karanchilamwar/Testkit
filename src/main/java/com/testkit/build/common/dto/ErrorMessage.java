package com.testkit.build.common.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.testkit.build.common.enums.ErrorCode;

import lombok.Data;

@Data

public class ErrorMessage {
	private int code;
	private String message;
	private LocalDate timpstamp = LocalDate.now();
	private List<DeveloperMessage> developerMessage = new ArrayList<DeveloperMessage>();

	public ErrorMessage() {

	}

	public ErrorMessage(ErrorCode errorCode) {
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
	}

	public ErrorMessage addDeveloperMessage(DeveloperMessage developerMessage) {
		this.developerMessage.add(developerMessage);
		return this;
	}

}
