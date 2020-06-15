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
import com.testkit.build.services.AdminService;

@RestController()
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	AdminService service;

	@PostMapping(value = "/addadmin")
	public AdminDTO addUser(@RequestBody AdminInDTO adminIndto) {
		return service.saveAdmin(adminIndto);
	}

	@GetMapping(value = "/getadmin")
	public List<AdminDTO> getAdmin() {
		return service.findAll();
	}

	@PutMapping(value = "/updateadmin/{userId}")
	public AdminDTO updateAdmin(@RequestBody AdminInDTO adminInDTO, @PathVariable int userId) {
		return service.updateAdmin(userId, adminInDTO);
	}
}
