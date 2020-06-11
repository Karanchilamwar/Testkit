package com.testkit.build.services;

import java.util.List;

import com.testkit.build.dto.ResourceDTO;
import com.testkit.build.dto.ResourceInDTO;
import com.testkit.build.exception.UserAvailableException;

public interface ResourceService {

	ResourceDTO saveResource(ResourceInDTO resourceInDTO) throws UserAvailableException;

	List<ResourceDTO> findAll();

	ResourceDTO updateResource(int userId, ResourceInDTO resourceInDTO);

}
