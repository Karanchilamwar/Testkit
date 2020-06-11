package com.testkit.build.exception;

public class UserAvailableException extends Exception {

	private static final long serialVersionUID = 6277511269832317527L;

	public UserAvailableException() {
		super("Already a user");

	}

}
