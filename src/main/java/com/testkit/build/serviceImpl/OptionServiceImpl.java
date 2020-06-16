package com.testkit.build.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.testkit.build.common.dto.DeveloperMessage;
import com.testkit.build.common.dto.ErrorMessage;
import com.testkit.build.common.enums.ErrorCode;
import com.testkit.build.common.exception.OptionAlreadyExistException;
import com.testkit.build.common.exception.OptionNotFoundException;
import com.testkit.build.dao.OptionRepository;
import com.testkit.build.dto.OptionDTO;
import com.testkit.build.dto.OptionInDTO;
import com.testkit.build.dto.OptionUpdateDTO;
import com.testkit.build.entity.OptionEntity;
import com.testkit.build.entity.QuestionEntity;
import com.testkit.build.mapper.OptionMapper;
import com.testkit.build.services.OptionService;

@Service
public class OptionServiceImpl implements OptionService {

	@Autowired
	OptionRepository optionRepository;

	@Lazy
	@Autowired
	OptionMapper optionMapper;

	@Autowired
	QuestionServiceImpl questionService;

	@Override
	public List<OptionEntity> saveOptions(List<OptionInDTO> optionInDTOs, QuestionEntity questionEntity) {
		List<OptionEntity> optionEntities = new ArrayList<OptionEntity>();
		validateOption(optionInDTOs, questionEntity);
		for (OptionInDTO optionInDTO : optionInDTOs) {
			OptionEntity optionEntity = createOptionEntity(optionInDTO);
			optionEntity.setQuestionEntity(questionEntity);
			optionEntities.add(optionEntity);
		}
		return optionRepository.saveAll(optionEntities);
	}

	@Override
	public List<OptionEntity> updateOptions(List<OptionUpdateDTO> optionUpdateDTOs, QuestionEntity questionEntity) {
		List<OptionEntity> existingOptionEntities = questionEntity.getOptionEntityList();
		OptionEntity optionEntity = null;

		deleteRemovedOptions(optionUpdateDTOs, existingOptionEntities);

		for (OptionUpdateDTO optionUpdateDTO : optionUpdateDTOs) {

			if (optionUpdateDTO.getId() == 0) {
				if (!isOptionAlreadyExists(optionUpdateDTO.getOptionText(), existingOptionEntities)) {
					optionEntity = createOptionEntities(optionUpdateDTO);
					optionEntity.setQuestionEntity(questionEntity);
					existingOptionEntities.add(optionEntity);
				}

			} else {
				optionEntity = findOptionEntityById(optionUpdateDTO.getId());
				optionEntity = updateOptionEnity(optionUpdateDTO, optionEntity);
				optionEntity.setQuestionEntity(questionEntity);
				existingOptionEntities.add(optionEntity);
			}

		}

		return optionRepository.saveAll(existingOptionEntities);
	}

	@Override
	public List<OptionDTO> getOptionsByQuestionEntity(int questionId) {
		List<OptionDTO> optionDTOs = new ArrayList<OptionDTO>();
		List<Optional<OptionEntity>> optionalsOptionList = optionRepository.findByQuestionId(questionId);
		if (!optionalsOptionList.isEmpty()) {
			throw new OptionNotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.OPTIONS_NOT_FOUND,
							"No option available in the database with ID{" + questionId + "}")));
		}

		for (Optional<OptionEntity> optional : optionalsOptionList) {
			if (optional.isPresent()) {
				optionDTOs.add(createOptionDTO(optional.get()));
			}
		}

		return optionDTOs;
	}

	@Override
	public boolean deleteOption(int optionId) {
		if (findOptionEntityById(optionId) != null) {
			throw new OptionNotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.OPTIONS_NOT_FOUND,
							"No Option available in the database with ID{" + optionId + "}")));
		}
		optionRepository.deleteById(optionId);
		return true;
	}

	@Override
	public void deleteOptions(List<OptionEntity> optionEntityList) {
		optionRepository.deleteAll(optionEntityList);
	}

	private void validateOption(List<OptionInDTO> optionInDTOs, QuestionEntity questionEntity) {
		for (OptionInDTO optionInDTO : optionInDTOs) {
			Optional<OptionEntity> optionalEntity = optionRepository
					.findOptionEntityByOptionTextAndQuestionEntity(optionInDTO.getOptionText(), questionEntity);
			if (optionalEntity.isPresent()) {
				throw new OptionAlreadyExistException(new ErrorMessage(ErrorCode.BAD_REQUEST)
						.addDeveloperMessage(new DeveloperMessage(ErrorCode.DUPLICATE_OPTION_ENTITY,
								"Option available in the database for same question")));
			}
		}
	}

	private OptionDTO createOptionDTO(OptionEntity optionEntity) {
		return optionMapper.optionEntityToOptionDTO(optionEntity);
	}

	private void deleteRemovedOptions(List<OptionUpdateDTO> optionUpdateDTOs,
			List<OptionEntity> existingOptionEntities) {
		Iterator<OptionEntity> optionEntityIterator = existingOptionEntities.iterator();
		while (optionEntityIterator.hasNext()) {
			OptionEntity optionEntity = optionEntityIterator.next();
			if (isOptionRemoved(optionEntity.getOptionText(), optionUpdateDTOs)) {
				optionRepository.delete(optionEntity);
				optionEntityIterator.remove();
			}
		}
	}

	private boolean isOptionRemoved(String optionText, List<OptionUpdateDTO> optionUpdateDTOs) {
		boolean isOptionRemoved = true;
		if (optionUpdateDTOs.stream().anyMatch(o -> o.getOptionText().equalsIgnoreCase(optionText))) {
			isOptionRemoved = false;
		}
		return isOptionRemoved;
	}

	private OptionEntity updateOptionEnity(OptionUpdateDTO optionUpdateDTO, OptionEntity optionEntity) {
		return optionMapper.updateOptionEntity(optionUpdateDTO, optionEntity);
	}

	private OptionEntity findOptionEntityById(Integer id) {
		OptionEntity optionEntity = null;
		Optional<OptionEntity> optional = findByOptionId(id);
		if (optional.isPresent())
			optionEntity = optional.get();

		return optionEntity;
	}

	private Optional<OptionEntity> findByOptionId(Integer id) {
		return optionRepository.findById(id);
	}

	private OptionEntity createOptionEntities(OptionUpdateDTO optionUpdateDTO) {
		return optionMapper.optionUpdateInDTOToOptionEntity(optionUpdateDTO);
	}

	private boolean isOptionAlreadyExists(String optionText, List<OptionEntity> existingOptionEntities) {
		return existingOptionEntities.stream().anyMatch(o -> o.getOptionText().equalsIgnoreCase(optionText));
	}

	private OptionEntity createOptionEntity(OptionInDTO optionInDTO) {
		return optionMapper.optionInDTOToOptionEntity(optionInDTO);
	}

}
