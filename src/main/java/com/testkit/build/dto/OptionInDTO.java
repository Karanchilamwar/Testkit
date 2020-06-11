package com.testkit.build.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionInDTO {

	private String optionText;

	private QuestionInDTO questionInDTO;
}
