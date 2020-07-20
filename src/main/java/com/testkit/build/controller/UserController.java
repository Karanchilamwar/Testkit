package com.testkit.build.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testkit.build.entity.UserEntity;
import com.testkit.build.serviceImpl.UserServiceImpl;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserServiceImpl service;

	@GetMapping
	public List<UserEntity> getAdmin() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public UserEntity getUser(@PathVariable int id) {
		return service.findById(id);
	}

}
