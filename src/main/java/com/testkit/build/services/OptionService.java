package com.testkit.build.services;

import java.util.List;

import com.testkit.build.dto.OptionDTO;
import com.testkit.build.dto.OptionInDTO;
import com.testkit.build.dto.OptionUpdateDTO;
import com.testkit.build.entity.OptionEntity;
import com.testkit.build.entity.QuestionEntity;

public interface OptionService {

	List<OptionEntity> saveOptions(List<OptionInDTO> optionInDTOs, QuestionEntity questionEntity);

	List<OptionEntity> updateOptions(List<OptionUpdateDTO> optionUpdateDTOs, QuestionEntity questionEntity);

	List<OptionDTO> getOptionsByQuestionEntity(int questionId);

	void deleteOptions(List<OptionEntity> optionEntityList);

	boolean deleteOption(int optionid);
}
