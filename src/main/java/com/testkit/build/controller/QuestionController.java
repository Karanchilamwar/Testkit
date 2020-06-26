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

import com.testkit.build.dto.QuestionDTO;
import com.testkit.build.dto.QuestionInDTO;
import com.testkit.build.dto.QuestionUpdateDTO;
import com.testkit.build.serviceImpl.QuestionServiceImpl;

@RestController
@RequestMapping(value = "questions")
public class QuestionController {

	@Autowired
	QuestionServiceImpl questionService;

	@GetMapping
	public List<QuestionDTO> get() {

		return questionService.find();
	}

	@PostMapping
	public QuestionDTO save(@RequestBody QuestionInDTO questionInDTO) {
		return questionService.save(questionInDTO);
	}

	@PutMapping("/{questionId}")
	public QuestionDTO update(@PathVariable int questionId, @RequestBody QuestionUpdateDTO questionUpdateDTO) {
		return questionService.update(questionId, questionUpdateDTO);
	}

	@GetMapping("/{questionId}")
	public QuestionDTO getQUestion(@PathVariable int questionId) {
		return questionService.findById(questionId);
	}

	@DeleteMapping(value = "{questionId}")
	public boolean deleteQuestion(@PathVariable int questionId) {
		return questionService.delete(questionId);
	}

}
