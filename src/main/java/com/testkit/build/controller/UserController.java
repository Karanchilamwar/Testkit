package com.testkit.build.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testkit.build.entity.UserEntity;
import com.testkit.build.services.UserService;

@RestController()
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService service;

	@GetMapping(value = "/getuser")
	public List<UserEntity> getAdmin() {
		return service.findAll();
	}

}
