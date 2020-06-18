package com.testkit.build.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testkit.build.dao.QuestionRepository;
import com.testkit.build.dto.QuestionDTO;
import com.testkit.build.dto.QuestionInDTO;
import com.testkit.build.dto.QuestionUpdateDTO;
import com.testkit.build.serviceImpl.QuestionServiceImpl;

@RestController
@RequestMapping(value = "question")
public class QuestionController {

	@Autowired
	QuestionServiceImpl questionService;

	@Autowired
	QuestionRepository questionRepository;

	@RequestMapping("/getquestion/{PageNumber}/{PageSize}")
	public List<QuestionDTO> getQuestionList(@PathVariable int PageNumber, @PathVariable int PageSize) {
		Pageable pageable = PageRequest.of(PageNumber, PageSize);
		return questionService.findQuestions(pageable);
	}

	@RequestMapping("/savequestion")
	public QuestionDTO saveQuestion(@RequestBody QuestionInDTO questionInDTO) {
		return questionService.saveQuestion(questionInDTO);
	}

	@PutMapping("/updatequestion/")
	public QuestionDTO updateQuestion(@RequestBody QuestionUpdateDTO questionUpdateDTO) {
		return questionService.updateQuestion(questionUpdateDTO);
	}

	@GetMapping("/getquestion/{questionId}")
	public QuestionDTO getQUestion(@PathVariable int questionId) {
		return questionService.findQuestionById(questionId);
	}

	@GetMapping("/type/{questionType}")
	public List<QuestionDTO> getQUestion(@PathVariable String questionType) {
		return questionService.findByType(questionType);
	}

	@DeleteMapping(value = "{questionId}")
	public boolean deleteQuestion(@PathVariable int questionId) {
		return questionService.deleteQuestion(questionId);
	}

	@GetMapping("/byoption/{optionText}")
	public List<QuestionDTO> findQuestionByOptionText(@PathVariable String optionText) {
		return questionService.findQuestionListByOptionText(optionText);
	}

}
