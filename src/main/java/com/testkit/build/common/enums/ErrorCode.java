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
	// Candidate
	CANDIDATE_NOT_FOUND(10013, "Candidate with email and mobile not found in the database"),
	// Question controller
	QUESTION_NOT_FOUND(1005, "Question entity not found"),
	DUPLICATE_QUESTION_ENTITY(1006, "Question with same Text and Type already in the database"),

	// Option controller
	OPTIONS_NOT_FOUND(1007, "Option is found"),
	DUPLICATE_OPTION_ENTITY(1008, "Option for the Question is in the database"),

	// Test Controller
	TEST_NAME_EXIST(1009, "Test Name already exists"), TEST_NOT_FOUND(1010, "No Test entities"),

	// Test Controller
	SECTION_NAME_EXIST(1011, "Section Name already exists"), SECTION_NOT_FOUND(1012, "No Section entities"),

	// Schedule Controller
	SCHEDULE_ALREADY_EXISTS(1012, "SCHEDULE ALREADY EXISTS"), SCHEDULE_NOT_EXISTS(1013, "SCHEDULE NOT EXISTS"),
	ANSWERSHEET_NOT_FOUND(1014, "NO ANSWER IN THE DATABASE");

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
