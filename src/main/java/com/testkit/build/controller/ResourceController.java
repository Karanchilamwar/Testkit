package com.testkit.build.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.testkit.build.serviceImpl.ResourceServiceImpl;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping(value = "/resources")
public class ResourceController {

	@Autowired
	ResourceServiceImpl service;

	@PostMapping
	public ResourceDTO addResource(@RequestBody ResourceInDTO candidateIndto) {
		return service.save(candidateIndto);
	}

	@GetMapping
	public List<ResourceDTO> getResource() {
		return service.findAll();
	}

	@GetMapping(value = "/{resourceId}")
	public ResourceDTO getResource(@PathVariable int resourceId) {
		return service.findById(resourceId);
	}

	@PutMapping(value = "/{resourceId}")
	public ResourceDTO updateResource(@PathVariable int resourceId, @RequestBody ResourceInDTO resourceInDTO) {
		return service.update(resourceId, resourceInDTO);
	}

	@DeleteMapping(value = "/{resourceId}")
	public boolean deleteResource(@PathVariable int resourceId) {
		return service.delete(resourceId);
	}

}
