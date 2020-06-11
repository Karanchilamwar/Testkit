package com.testkit.build.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.testkit.build.dto.ResourceDTO;
import com.testkit.build.dto.ResourceInDTO;
import com.testkit.build.entity.ResourceEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ResourceMapper {

	@Mapping(source = "userName", target = "userName")

	@Mapping(source = "userEmail", target = "userEmail")

	@Mapping(source = "userPassword", target = "userPassword")

	@Mapping(source = "userMobile", target = "userMobile")

	@Mapping(source = "registrationDate", target = "registrationDate")

	ResourceEntity ResourceInDTOToResourceEntity(ResourceInDTO resourceInDTO);

	@Mapping(source = "userName", target = "userName")
	@Mapping(source = "userEmail", target = "userEmail")
	@Mapping(source = "userMobile", target = "userMobile")
	@Mapping(source = "registrationDate", target = "registrationDate")

	ResourceDTO ResourceEntityTOResourceDTO(ResourceEntity resourceEntity);

	ResourceEntity resourceInDTOToResourceEntity(ResourceInDTO resourceInDTO,
			@MappingTarget ResourceEntity resourceEntity);
}
