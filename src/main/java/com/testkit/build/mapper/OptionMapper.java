package com.testkit.build.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.testkit.build.dto.OptionDTO;
import com.testkit.build.dto.OptionInDTO;
import com.testkit.build.dto.OptionUpdateDTO;
import com.testkit.build.entity.OptionEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OptionMapper {

	// OptionEntity optionInDTOToOptionEntity(OptionInDTO optionInDTO);

	OptionEntity optionInDTOToOptionEntity(OptionInDTO optionInDTO);

	OptionDTO optionEntityToOptionDTO(OptionEntity optionEntity);

	OptionEntity updateOptionEntity(OptionUpdateDTO optionUpdateDTO, @MappingTarget OptionEntity optionEntity);

	OptionEntity optionUpdateInDTOToOptionEntity(OptionUpdateDTO optionUpdateDTO);

}
