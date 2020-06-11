package com.testkit.build.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testkit.build.serviceImpl.OptionServiceImpl;

@RestController
@RequestMapping("/option")
public class OptionController {

	@Autowired
	OptionServiceImpl optionService;

}
