package com.testkit.build.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.testkit.build.dto.QuestionDTO;
import com.testkit.build.dto.QuestionInDTO;
import com.testkit.build.dto.QuestionUpdateDTO;
import com.testkit.build.entity.QuestionEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface QuestionMapper {

	@Mapping(source = "questionInDTO.optionInDTOList", target = "optionEntityList")
	QuestionEntity questionInDTOToQuestionEntity(QuestionInDTO questionInDTO);

	@Mapping(source = "questionEntity.optionEntityList", target = "optionDTOList")
	QuestionDTO questionEntityToQuestionDTO(QuestionEntity questionEntity);

	@Mapping(source = "questionInDTO.optionInDTOList", target = "questionEntity.optionEntityList")
	QuestionEntity updateQuestionEntity(QuestionInDTO questionInDTO, @MappingTarget QuestionEntity questionEntity);

	@Mapping(source = "questionUpdateDTO.optionUpdateDTOs", target = "questionEntity.optionEntityList")
	QuestionEntity updateQuestionEntity(QuestionUpdateDTO questionUpdateDTO,
			@MappingTarget QuestionEntity questionEntity);
}
