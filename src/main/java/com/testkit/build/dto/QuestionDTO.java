package com.testkit.build.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

	private int id;

	private String questionText;

	private int time;

	private String type;

	private String answer;

	List<OptionDTO> optionDTOList;
}
