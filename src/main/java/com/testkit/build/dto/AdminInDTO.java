package com.testkit.build.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AdminInDTO extends UserInDTO {

	private String adminLevel;

}
