package com.testkit.build.dto;

import com.testkit.build.common.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class UserDTO {

	private int id;

	private String userName;

	private String userEmail;

	private String userPassword;

	private String userMobile;

	private UserType userType;
}
