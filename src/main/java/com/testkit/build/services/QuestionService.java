package com.testkit.build.services;

import java.util.List;

import com.testkit.build.dto.QuestionDTO;
import com.testkit.build.dto.QuestionInDTO;
import com.testkit.build.dto.QuestionUpdateDTO;

public interface QuestionService {

	QuestionDTO save(QuestionInDTO questionInDTO);

	QuestionDTO update(int questionId, QuestionUpdateDTO questionUpdateDTO);

	List<QuestionDTO> find();

	QuestionDTO findById(int questionId);

	boolean delete(int questionid);
}
