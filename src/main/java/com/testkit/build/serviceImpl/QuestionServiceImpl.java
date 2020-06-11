package com.testkit.build.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.testkit.build.dao.QuestionRepository;
import com.testkit.build.dto.OptionUpdateDTO;
import com.testkit.build.dto.QuestionDTO;
import com.testkit.build.dto.QuestionInDTO;
import com.testkit.build.dto.QuestionUpdateDTO;
import com.testkit.build.entity.OptionEntity;
import com.testkit.build.entity.QuestionEntity;
import com.testkit.build.exception.QuestionAlreadyPresent;
import com.testkit.build.mapper.QuestionMapper;
import com.testkit.build.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Lazy
	@Autowired
	QuestionMapper questionMapper;

	@Autowired
	OptionServiceImpl optionService;

	@Override
	public QuestionDTO saveQuestion(QuestionInDTO questionInDTO) {
		validateQuestion(questionInDTO);
		QuestionEntity questionEntity = createQuestionEntity(questionInDTO);
		questionEntity = questionRepository.save(questionEntity);
		questionEntity.setOptionEntityList(saveOptionsEntity(questionInDTO, questionEntity));
		return createQuestionDTO(questionRepository.save(questionEntity));
	}

	@Override
	public QuestionDTO updateQuestion(QuestionUpdateDTO questionUpdateDTO) {
		QuestionDTO questionDTO = null;
		Optional<QuestionEntity> optionalQuestionEntity = questionRepository.findById(questionUpdateDTO.getId());
		if (optionalQuestionEntity.isPresent()) {
			QuestionEntity questionEntity = optionalQuestionEntity.get();
			questionEntity = this.updateQuestionEntity(questionUpdateDTO, questionEntity);
			questionEntity = questionRepository.save(questionEntity);
			questionEntity
					.setOptionEntityList(updateOptionEntity(questionUpdateDTO.getOptionUpdateDTOs(), questionEntity));
			questionDTO = this.createQuestionDTO(questionEntity);
		}
		return questionDTO;
	}

	@Override
	public List<QuestionDTO> findQuestions() {
		List<QuestionEntity> questionEntityList = new ArrayList<QuestionEntity>();
		questionRepository.findAll().forEach(questionEntityList::add);

		return createQuestionDTOList(questionEntityList);
	}

	@Override
	public QuestionDTO findQuestionById(int questionId) {
		QuestionDTO questionDTO = null;
		Optional<QuestionEntity> optionalQuestionEntity = questionRepository.findById(questionId);
		if (optionalQuestionEntity.isPresent()) {
			questionDTO = createQuestionDTO(optionalQuestionEntity.get());
		}
		return questionDTO;
	}

	private QuestionEntity createQuestionEntity(QuestionInDTO questionInDTO) {
		return questionMapper.questionInDTOToQuestionEntity(questionInDTO);
	}

	private List<QuestionDTO> createQuestionDTOList(List<QuestionEntity> optionEntityList) {
		List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();

		for (QuestionEntity questionEntity : optionEntityList) {
			questionDTOList.add(createQuestionDTO(questionEntity));
		}
		return questionDTOList;
	}

	private List<OptionEntity> updateOptionEntity(List<OptionUpdateDTO> optionUpdateDTOs,
			QuestionEntity questionEntity) {
		return optionService.updateOptions(optionUpdateDTOs, questionEntity);
	}

	private List<OptionEntity> saveOptionsEntity(QuestionInDTO questionInDTO, QuestionEntity questionEntity) {
		return optionService.saveOptions(questionInDTO.getOptionInDTOList(), questionEntity);
	}

	private QuestionEntity updateQuestionEntity(QuestionUpdateDTO questionUpdateDTO, QuestionEntity questionEntity) {
		return questionMapper.updateQuestionEntity(questionUpdateDTO, questionEntity);
	}

	private QuestionDTO createQuestionDTO(QuestionEntity questionEntity) {
		return questionMapper.questionEntityToQuestionDTO(questionEntity);
	}

	private boolean validateQuestion(QuestionInDTO questionInDTO) {
		QuestionEntity questionEntity = questionRepository
				.findQuestionEntityByQuestionTextAndType(questionInDTO.getQuestionText(), questionInDTO.getType());
		if (questionEntity != null) {
			try {
				throw new QuestionAlreadyPresent();
			} catch (QuestionAlreadyPresent questionAlreadyPresent) {

			}
		}
		return true;
	}

}
