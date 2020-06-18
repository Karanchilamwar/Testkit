package com.testkit.build.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.testkit.build.dto.QuestionDTO;
import com.testkit.build.dto.QuestionInDTO;
import com.testkit.build.dto.QuestionUpdateDTO;

public interface QuestionService {

	QuestionDTO saveQuestion(QuestionInDTO questionInDTO);

	QuestionDTO updateQuestion(QuestionUpdateDTO questionUpdateDTO);

	List<QuestionDTO> findQuestions(Pageable pageable);

	QuestionDTO findQuestionById(int questionId);

	boolean deleteQuestion(int questionid);

	List<QuestionDTO> findQuestionListByOptionText(String optiontText);
}
