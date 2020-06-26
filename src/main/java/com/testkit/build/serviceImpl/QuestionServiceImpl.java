package com.testkit.build.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.testkit.build.common.dto.DeveloperMessage;
import com.testkit.build.common.dto.ErrorMessage;
import com.testkit.build.common.enums.ErrorCode;
import com.testkit.build.common.exception.BadRequestException;
import com.testkit.build.common.exception.NotFoundException;
import com.testkit.build.dao.QuestionRepository;
import com.testkit.build.dto.OptionUpdateDTO;
import com.testkit.build.dto.QuestionDTO;
import com.testkit.build.dto.QuestionInDTO;
import com.testkit.build.dto.QuestionUpdateDTO;
import com.testkit.build.entity.OptionEntity;
import com.testkit.build.entity.QuestionEntity;
import com.testkit.build.mapper.QuestionMapper;
import com.testkit.build.predicates.QuestionPredicate;
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
	public QuestionDTO save(QuestionInDTO questionInDTO) {
		validateQuestion(questionInDTO);
		QuestionEntity questionEntity = createQuestionEntity(questionInDTO);
		questionEntity = questionRepository.save(questionEntity);
		questionEntity.setOptionEntityList(saveOptionsEntity(questionInDTO, questionEntity));
		return createQuestionDTO(questionRepository.save(questionEntity));
	}

	@Override
	public QuestionDTO update(int questionId, QuestionUpdateDTO questionUpdateDTO) {
		QuestionDTO questionDTO = null;
		QuestionEntity questionEntity = find(questionId);
		questionEntity = this.updateQuestionEntity(questionUpdateDTO, questionEntity);
		questionEntity = questionRepository.save(questionEntity);
		questionEntity.setOptionEntityList(updateOptionEntity(questionUpdateDTO.getOptionUpdateDTOs(), questionEntity));
		questionDTO = this.createQuestionDTO(questionEntity);

		return questionDTO;
	}

	@Override
	public List<QuestionDTO> find() {
		List<QuestionEntity> questionEntityList = new ArrayList<QuestionEntity>();
		questionRepository.findAll().forEach(questionEntityList::add);
		if (questionEntityList.isEmpty()) {
			throw new NotFoundException(
					new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION).addDeveloperMessage(new DeveloperMessage(
							ErrorCode.QUESTION_NOT_FOUND, "No Questiona available in the database with ID")));
		}
		return createQuestionDTOList(questionEntityList);
	}

	@Override
	public QuestionDTO findById(int questionId) {
		return createQuestionDTO(this.find(questionId));
	}

	@Override
	public boolean delete(int questionId) {
		find(questionId);
		questionRepository.deleteById(questionId);
		return true;
	}

	private QuestionEntity find(int questionId) {
		Optional<QuestionEntity> optionalQuestionEntity = questionRepository.findById(questionId);
		if (!optionalQuestionEntity.isPresent()) {
			throw new NotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.QUESTION_NOT_FOUND,
							"No Question available in the database with ID{" + questionId + "}")));
		}

		QuestionEntity questionEntity = optionalQuestionEntity.get();
		return questionEntity;
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
		return optionService.update(optionUpdateDTOs, questionEntity);
	}

	private List<OptionEntity> saveOptionsEntity(QuestionInDTO questionInDTO, QuestionEntity questionEntity) {
		return optionService.save(questionInDTO.getOptionInDTOList(), questionEntity);
	}

	private QuestionEntity updateQuestionEntity(QuestionUpdateDTO questionUpdateDTO, QuestionEntity questionEntity) {
		return questionMapper.updateQuestionEntity(questionUpdateDTO, questionEntity);
	}

	private QuestionDTO createQuestionDTO(QuestionEntity questionEntity) {
		return questionMapper.questionEntityToQuestionDTO(questionEntity);
	}

	private boolean validateQuestion(QuestionInDTO questionInDTO) {
		QuestionEntity questionEntity = findQuestionEntityByQuestionTextAndType(questionInDTO.getQuestionText(),
				questionInDTO.getType());
		if (questionEntity != null) {
			throw new BadRequestException(new ErrorMessage(ErrorCode.BAD_REQUEST)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.DUPLICATE_QUESTION_ENTITY,
							"Question entity with same text contain and type already exists")));
		}
		return true;
	}

	private QuestionEntity findQuestionEntityByQuestionTextAndType(String questionText, String type) {
		QuestionEntity questionEntity = null;
		BooleanExpression questionTextExp = QuestionPredicate.questionTextEq(questionText);
		BooleanExpression questionTypeExp = QuestionPredicate.questionTypeEq(type);
		Optional<QuestionEntity> optional = questionRepository.findOne(questionTextExp.and(questionTypeExp));
		if (optional.isPresent()) {
			questionEntity = optional.get();
		}
		return questionEntity;
	}

}
