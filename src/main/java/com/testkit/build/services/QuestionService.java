package com.testkit.build.services;

import java.util.List;

import com.testkit.build.dto.QuestionDTO;
import com.testkit.build.dto.QuestionInDTO;
import com.testkit.build.dto.QuestionUpdateDTO;

public interface QuestionService {

	QuestionDTO saveQuestion(QuestionInDTO questionInDTO);

	QuestionDTO updateQuestion(QuestionUpdateDTO questionUpdateDTO);

	List<QuestionDTO> findQuestions();

	QuestionDTO findQuestionById(int questionId);
}
