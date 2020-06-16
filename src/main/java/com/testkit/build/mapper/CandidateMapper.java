package com.testkit.build.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.testkit.build.dto.CandidateDTO;
import com.testkit.build.dto.CandidateInDTO;
import com.testkit.build.entity.CandidateEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CandidateMapper {

	@InheritConfiguration
	@Mapping(source = "userName", target = "userName")
	@Mapping(source = "userEmail", target = "userEmail")
	@Mapping(source = "userPassword", target = "userPassword")
	@Mapping(source = "userMobile", target = "userMobile")
	@Mapping(source = "registrationDate", target = "registrationDate")
	CandidateEntity CandidateInDTOToCandidateEntity(CandidateInDTO candidateInDTO);

	@InheritConfiguration
	@Mapping(source = "userName", target = "userName")
	@Mapping(source = "userEmail", target = "userEmail")
	@Mapping(source = "userMobile", target = "userMobile")
	@Mapping(source = "registrationDate", target = "registrationDate")
	CandidateDTO CandidateEntityTOCandidateDTO(CandidateEntity candidateEntity);

	CandidateEntity CandidateInDTOToCandidateEntity(CandidateInDTO candidateInDTO,
			@MappingTarget CandidateEntity candidateEntity);

}
