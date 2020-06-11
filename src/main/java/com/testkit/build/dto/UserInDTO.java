package com.testkit.build.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class UserInDTO {

	private String userName;

	private String userEmail;

	private String userPassword;

	private String userMobile;
}
