package com.testkit.build.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testkit.build.serviceImpl.OptionServiceImpl;

@RestController
@RequestMapping("/option")
public class OptionController {

	@Autowired
	OptionServiceImpl optionService;

	@DeleteMapping(value = "{userId}")
	public boolean deleteOption(@PathVariable int optionId) {
		return optionService.deleteOption(optionId);
	}
}
