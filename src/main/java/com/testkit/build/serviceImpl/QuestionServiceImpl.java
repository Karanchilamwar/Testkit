package com.testkit.build.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.testkit.build.common.dto.DeveloperMessage;
import com.testkit.build.common.dto.ErrorMessage;
import com.testkit.build.common.enums.ErrorCode;
import com.testkit.build.common.exception.QuestionAlreadyExistException;
import com.testkit.build.common.exception.QuestionNotFoundException;
import com.testkit.build.dao.QuestionRepository;
import com.testkit.build.dto.OptionUpdateDTO;
import com.testkit.build.dto.QuestionDTO;
import com.testkit.build.dto.QuestionInDTO;
import com.testkit.build.dto.QuestionUpdateDTO;
import com.testkit.build.entity.OptionEntity;
import com.testkit.build.entity.QQuestionEntity;
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
		if (!optionalQuestionEntity.isPresent()) {
			throw new QuestionNotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.QUESTION_NOT_FOUND,
							"No Question available in the database with ID{" + questionUpdateDTO.getId() + "}")));
		}

		QuestionEntity questionEntity = optionalQuestionEntity.get();
		questionEntity = this.updateQuestionEntity(questionUpdateDTO, questionEntity);
		questionEntity = questionRepository.save(questionEntity);
		questionEntity.setOptionEntityList(updateOptionEntity(questionUpdateDTO.getOptionUpdateDTOs(), questionEntity));
		questionDTO = this.createQuestionDTO(questionEntity);

		return questionDTO;
	}

	@Override
	public List<QuestionDTO> findQuestions(Pageable pageable) {
		List<QuestionEntity> questionEntityList = new ArrayList<QuestionEntity>();
		questionRepository.findAll().forEach(questionEntityList::add);
		if (questionEntityList.isEmpty()) {
			throw new QuestionNotFoundException(
					new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION).addDeveloperMessage(new DeveloperMessage(
							ErrorCode.QUESTION_NOT_FOUND, "No Questiona available in the database with ID")));
		}
		return createQuestionDTOList(questionEntityList);
	}

	@Override
	public QuestionDTO findQuestionById(int questionId) {
		QuestionDTO questionDTO = null;
		Optional<QuestionEntity> optionalQuestionEntity = questionRepository.findById(questionId);
		if (optionalQuestionEntity.isPresent()) {
			throw new QuestionNotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.QUESTION_NOT_FOUND,
							"No Question available in the database with ID{" + questionId + "}")));
		}
		questionDTO = createQuestionDTO(optionalQuestionEntity.get());
		return questionDTO;
	}

	@Override
	public boolean deleteQuestion(int questionId) {
		if (findQuestionById(questionId) != null) {
			throw new QuestionNotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.QUESTION_NOT_FOUND,
							"No Question available in the database with ID{" + questionId + "}")));
		}
		questionRepository.deleteById(questionId);
		return true;
	}

	@Override
	public List<QuestionDTO> findQuestionListByOptionText(String optionText) {
		List<QuestionEntity> questionEntityList = new ArrayList<>();
		QQuestionEntity qQuestionEntity = QQuestionEntity.questionEntity;
		BooleanExpression optionTextExp = QuestionPredicate.questionByOptionExp(optionText);
		questionRepository.findAll(optionTextExp).forEach(questionEntityList::add);
		if (questionEntityList.isEmpty()) {
			throw new QuestionNotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.QUESTION_NOT_FOUND,
							"No questions found with the option text {" + optionText + "}")));
		}
		return createQuestionDTOList(questionEntityList);
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
		QuestionEntity questionEntity = findQuestionEntityByQuestionTextAndType(questionInDTO.getQuestionText(),
				questionInDTO.getType());
		if (questionEntity != null) {
			throw new QuestionAlreadyExistException(new ErrorMessage(ErrorCode.BAD_REQUEST)
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

	public List<QuestionDTO> findByType(String questionType) {
		List<QuestionEntity> questionEntityList = new ArrayList<QuestionEntity>();
		QQuestionEntity qQuestionEntity = QQuestionEntity.questionEntity;

		BooleanExpression questionTypeExp = QuestionPredicate.questionTypeEq(questionType);
		questionRepository.findAll(questionTypeExp).forEach(questionEntityList::add);
		if (questionEntityList.isEmpty()) {
			throw new QuestionNotFoundException(
					new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION).addDeveloperMessage(new DeveloperMessage(
							ErrorCode.QUESTION_NOT_FOUND, "No questions found with the type {" + questionType + "}")));
		}
		return createQuestionDTOList(questionEntityList);
	}

}
