package com.testkit.build.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface UserMapper {

	/*
	 * @Mapping(source="userName",target="userName")
	 * 
	 * @Mapping(source="userEmail",target="userEmail")
	 * 
	 * @Mapping(source="userPassword",target="userPassword")
	 * 
	 * @Mapping(source="userMobile",target="userMobile")
	 * 
	 * @Mapping(source="id",target="id")
	 * 
	 * UserEntity UserInDTOToUserEntity(UserInDTO userInDTO);
	 * 
	 * 
	 * @Mapping(source = "userName", target = "userName")
	 * 
	 * @Mapping(source = "userEmail", target = "userEmail")
	 * 
	 * @Mapping(source = "userMobile", target = "userMobile")
	 * 
	 * UserDTO UserEntityTOUserDTO(UserEntity userEntity);
	 */

}
