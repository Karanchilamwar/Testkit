package com.testkit.build.dto;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CandidateInDTO extends UserInDTO {

	private Date registrationDate;

}
