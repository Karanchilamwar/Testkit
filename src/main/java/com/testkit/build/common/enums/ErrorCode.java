package com.testkit.build.common.enums;

public enum ErrorCode {

	// Common error code
	VALIDATION_ERROR(100, "VALIDATION_ERROR"), NOT_FOUND_EXCEPTION(101, "NOT FOUND EXCEPTION"),
	AUTHORIZATION_ERROR(105, "NO_AUTHORIZATION"), BAD_REQUEST(106, "BAD REQUEST"),
	PROCESSING_ERROR(12, "PROCESSING_ERROR"),
	// User controller
	USER_ALREADY_EXISTS(1000, "User with same mobile or email already exist"),
	USER_DATA_VALIDATION(1002, "Provided user data is not valid"),
	USER_LOGIN_VALIDATION(1004, "Login denied with given username and password"),
	USER_DELETION_FAILED(1004, "Deletion did not completed"),

	// Question controller
	QUESTION_NOT_FOUND(1005, "Question entity not found"),
	DUPLICATE_QUESTION_ENTITY(1006, "Question with same Text and Type already in the database"),

	// Option controller
	OPTIONS_NOT_FOUND(1007, "Option is found"),
	DUPLICATE_OPTION_ENTITY(1008, "Option for the Question is in the database");

	private int code;
	private String message;

	ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
