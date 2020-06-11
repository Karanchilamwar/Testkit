package com.testkit.build.dto;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ResourceInDTO extends UserInDTO {

	private Date registrationDate;

}
