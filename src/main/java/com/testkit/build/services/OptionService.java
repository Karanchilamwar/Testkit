package com.testkit.build.services;

import java.util.List;

import com.testkit.build.dto.OptionDTO;
import com.testkit.build.dto.OptionInDTO;
import com.testkit.build.dto.OptionUpdateDTO;
import com.testkit.build.entity.OptionEntity;
import com.testkit.build.entity.QuestionEntity;

public interface OptionService {

	List<OptionEntity> save(List<OptionInDTO> optionInDTOs, QuestionEntity questionEntity);

	List<OptionEntity> update(List<OptionUpdateDTO> optionUpdateDTOs, QuestionEntity questionEntity);

	List<OptionDTO> getByQuestionEntity(int questionId);

	void delete(List<OptionEntity> optionEntityList);

	boolean delete(int optionid);
}
