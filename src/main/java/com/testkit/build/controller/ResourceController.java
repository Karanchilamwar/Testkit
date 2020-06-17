package com.testkit.build.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testkit.build.dto.ResourceDTO;
import com.testkit.build.dto.ResourceInDTO;
import com.testkit.build.services.ResourceService;

@RestController()
@RequestMapping(value = "/resource")
public class ResourceController {

	@Autowired
	ResourceService service;

	@PostMapping(value = "/addresource")
	public ResourceDTO addResource(@RequestBody ResourceInDTO candidateIndto) {
		return service.saveResource(candidateIndto);
	}

	@GetMapping(value = "/getresource")
	public List<ResourceDTO> getResource() {
		return service.findAll();
	}

	@PutMapping(value = "/updatecandidate/{userId}")
	public ResourceDTO updateAdmin(@RequestBody ResourceInDTO resourceInDTO, @PathVariable int userId) {
		return service.updateResource(userId, resourceInDTO);
	}

	@DeleteMapping(value = "{questionId}")
	public boolean deleteResource(@PathVariable int resourceId) {
		return service.deleteResource(resourceId);
	}

}
