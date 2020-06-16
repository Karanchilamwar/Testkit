package com.testkit.build.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.testkit.build.common.dto.DeveloperMessage;
import com.testkit.build.common.dto.ErrorMessage;
import com.testkit.build.common.enums.ErrorCode;
import com.testkit.build.common.exception.UserAvailableException;
import com.testkit.build.dao.CandidateRepository;
import com.testkit.build.dto.CandidateDTO;
import com.testkit.build.dto.CandidateInDTO;
import com.testkit.build.entity.CandidateEntity;
import com.testkit.build.mapper.CandidateMapper;
import com.testkit.build.services.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	CandidateRepository candidateRepository;

	@Lazy
	@Autowired
	CandidateMapper mapper;

	@Override
	public CandidateDTO saveCandidate(CandidateInDTO candidateInDTO) {

		validateCandidate(candidateInDTO);
		return this.createCandidateDTO(candidateRepository.save(this.createCandidateEntity(candidateInDTO)));

	}

	@Override
	public List<CandidateDTO> findAll() {
		List<CandidateEntity> list = new ArrayList<>();
		candidateRepository.findAll().forEach(list::add);
		return createCandidateDTOS(list);
	}

	public List<CandidateDTO> createCandidateDTOS(List<CandidateEntity> list) {
		List<CandidateDTO> candDtos = new ArrayList<>();
		for (CandidateEntity candidateEntity : list) {
			candDtos.add(createCandidateDTO(candidateEntity));
		}
		return candDtos;
	}

	@Override
	public CandidateDTO updateCandidate(int userId, CandidateInDTO candidateInDTO) {
		CandidateDTO candidateDTO = null;

		Optional<CandidateEntity> optionalCandidateEntity = candidateRepository.findById(userId);

		if (optionalCandidateEntity.isPresent()) {
			CandidateEntity candidateEntity = optionalCandidateEntity.get();
			candidateEntity = updateCandidateEntity(candidateInDTO, candidateEntity);
			candidateDTO = createCandidateDTO(candidateRepository.save(candidateEntity));
		}
		return candidateDTO;
	}

	private CandidateEntity updateCandidateEntity(CandidateInDTO candidateInDTO, CandidateEntity candidateEntity) {
		return mapper.CandidateInDTOToCandidateEntity(candidateInDTO, candidateEntity);
	}

	private CandidateEntity findUserByUserEmailOrUserMobile(String userEmail, String userMobile) {
		return candidateRepository.findCandidateEntityByUserEmailOrUserMobile(userEmail, userMobile);
	}

	private boolean validateCandidate(CandidateInDTO candidateInDTO) {
		CandidateEntity candidateEntity = (CandidateEntity) this
				.findUserByUserEmailOrUserMobile(candidateInDTO.getUserEmail(), candidateInDTO.getUserMobile());
		if (candidateEntity != null) {
			throw new UserAvailableException(new ErrorMessage(ErrorCode.VALIDATION_ERROR)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.USER_ALREADY_EXISTS)));
		}
		return true;
	}

	private CandidateEntity createCandidateEntity(CandidateInDTO candidateInDTO) {
		return mapper.CandidateInDTOToCandidateEntity(candidateInDTO);
	}

	private CandidateDTO createCandidateDTO(CandidateEntity candidateEntity) {
		return mapper.CandidateEntityTOCandidateDTO(candidateEntity);
	}

}
