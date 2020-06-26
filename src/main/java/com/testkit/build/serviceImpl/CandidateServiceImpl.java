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
import com.testkit.build.dao.CandidateRepository;
import com.testkit.build.dto.CandidateDTO;
import com.testkit.build.dto.CandidateInDTO;
import com.testkit.build.entity.CandidateEntity;
import com.testkit.build.mapper.CandidateMapper;
import com.testkit.build.predicates.CandidatePredicate;
import com.testkit.build.services.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	CandidateRepository candidateRepository;

	@Lazy
	@Autowired
	CandidateMapper mapper;

	@Override
	public CandidateDTO save(CandidateInDTO candidateInDTO) {

		validateCandidate(candidateInDTO);
		return this.createCandidateDTO(candidateRepository.save(this.createCandidateEntity(candidateInDTO)));

	}

	@Override
	public List<CandidateDTO> findAll() {
		List<CandidateEntity> list = new ArrayList<>();
		candidateRepository.findAll().forEach(list::add);
		if (list.isEmpty()) {
			throw new NotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION).addDeveloperMessage(
					new DeveloperMessage(ErrorCode.NOT_FOUND_EXCEPTION, "No user available in the database")));
		}
		return createCandidateDTOS(list);
	}

	@Override
	public CandidateDTO update(int userId, CandidateInDTO candidateInDTO) {
		CandidateDTO candidateDTO = null;

		CandidateEntity candidateEntity = find(userId);
		candidateEntity = updateCandidateEntity(candidateInDTO, candidateEntity);
		candidateDTO = createCandidateDTO(candidateRepository.save(candidateEntity));

		return candidateDTO;
	}

	@Override
	public boolean delete(int userid) {
		getCandidateEntityById(userid);
		candidateRepository.deleteById(userid);
		return true;
	}

	@Override
	public CandidateDTO findById(int candidateId) {
		return createCandidateDTO(this.find(candidateId));
	}

	private CandidateEntity find(int userId) {
		Optional<CandidateEntity> optionalCandidateEntity = candidateRepository.findById(userId);

		if (!optionalCandidateEntity.isPresent()) {
			throw new NotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.NOT_FOUND_EXCEPTION,
							"No user available in the database with ID{" + userId + "}")));
		}
		CandidateEntity candidateEntity = optionalCandidateEntity.get();
		return candidateEntity;
	}

	private List<CandidateDTO> createCandidateDTOS(List<CandidateEntity> list) {
		List<CandidateDTO> candDtos = new ArrayList<>();
		for (CandidateEntity candidateEntity : list) {
			candDtos.add(createCandidateDTO(candidateEntity));
		}
		return candDtos;
	}

	private CandidateEntity getCandidateEntityById(int userId) {
		CandidateEntity candidateEntity = null;
		BooleanExpression candidateIdExp = CandidatePredicate.userIdEq(userId);
		Optional<CandidateEntity> optional = candidateRepository.findOne(candidateIdExp);
		if (optional.isPresent()) {
			candidateEntity = optional.get();
		} else {
			throw new NotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.NOT_FOUND_EXCEPTION,
							"No user available in the database with ID{" + userId + "}")));
		}
		return candidateEntity;
	}

	private CandidateEntity updateCandidateEntity(CandidateInDTO candidateInDTO, CandidateEntity candidateEntity) {
		return mapper.CandidateInDTOToCandidateEntity(candidateInDTO, candidateEntity);
	}

	private CandidateEntity findUserByUserEmailOrUserMobile(String userEmail, String userMobile) {
		return candidateRepository.findCandidateEntityByUserEmailOrUserMobile(userEmail, userMobile);
	}

	private boolean validateCandidate(CandidateInDTO candidateInDTO) {
		CandidateEntity candidateEntity = this.findUserByUserEmailOrUserMobile(candidateInDTO.getUserEmail(),
				candidateInDTO.getUserMobile());
		if (candidateEntity != null) {

			throw new BadRequestException(new ErrorMessage(ErrorCode.BAD_REQUEST).addDeveloperMessage(
					new DeveloperMessage(ErrorCode.USER_ALREADY_EXISTS, "User is already registered, try log-in")));

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
