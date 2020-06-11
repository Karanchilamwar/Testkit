package com.testkit.build.dto;

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
}
