package com.testkit.build.services;

import java.util.List;

import com.testkit.build.dto.ResourceDTO;
import com.testkit.build.dto.ResourceInDTO;

public interface ResourceService {

	ResourceDTO save(ResourceInDTO resourceInDTO);

	List<ResourceDTO> findAll();

	ResourceDTO update(int userId, ResourceInDTO resourceInDTO);

	boolean delete(int userid);

	ResourceDTO findById(int userId);
}
