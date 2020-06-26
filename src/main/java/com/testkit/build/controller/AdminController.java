package com.testkit.build.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testkit.build.dto.AdminDTO;
import com.testkit.build.dto.AdminInDTO;
import com.testkit.build.serviceImpl.AdminServiceImpl;

@RestController()
@RequestMapping(value = "/admins")
public class AdminController {

	@Autowired
	AdminServiceImpl service;

	@PostMapping
	public AdminDTO addUser(@RequestBody AdminInDTO adminIndto) {
		return service.save(adminIndto);
	}

	@GetMapping
	public List<AdminDTO> getAdmin() {
		return service.findAll();
	}

	@GetMapping(value = "/{adminId}")
	public AdminDTO getAdmin(@PathVariable int adminId) {
		return service.findById(adminId);
	}

	@PutMapping(value = "/{userId}")
	public AdminDTO updateAdmin(@RequestBody AdminInDTO adminInDTO, @PathVariable int userId) {
		return service.update(userId, adminInDTO);
	}
}
