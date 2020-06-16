package com.testkit.build.services;

import java.util.List;

import com.testkit.build.dto.ResourceDTO;
import com.testkit.build.dto.ResourceInDTO;

public interface ResourceService {

	ResourceDTO saveResource(ResourceInDTO resourceInDTO);

	List<ResourceDTO> findAll();

	ResourceDTO updateResource(int userId, ResourceInDTO resourceInDTO);

}
