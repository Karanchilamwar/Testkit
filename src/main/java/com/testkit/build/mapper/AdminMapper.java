package com.testkit.build.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.testkit.build.dto.AdminDTO;
import com.testkit.build.dto.AdminInDTO;
import com.testkit.build.entity.AdminEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdminMapper {

	@Mapping(source = "userName", target = "userName")
	@Mapping(source = "userEmail", target = "userEmail")
	@Mapping(source = "userPassword", target = "userPassword")
	@Mapping(source = "userMobile", target = "userMobile")
	@Mapping(source = "adminLevel", target = "adminLevel")
	AdminEntity AdminInDTOToAdmin(AdminInDTO adminInDTO);

	@Mapping(source = "userName", target = "userName")
	@Mapping(source = "userEmail", target = "userEmail")
	@Mapping(source = "userMobile", target = "userMobile")
	@Mapping(source = "adminLevel", target = "adminLevel")
	AdminDTO AdminEntityTOAdminDTO(AdminEntity adminEntity);

	AdminEntity adminInDTOToAdminEntity(AdminInDTO adminInDTO, @MappingTarget AdminEntity adminEntity);

}
