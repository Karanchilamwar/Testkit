package com.testkit.build.services;

import java.util.List;

import com.testkit.build.dto.CandidateDTO;
import com.testkit.build.dto.CandidateInDTO;
import com.testkit.build.exception.UserAvailableException;

public interface CandidateService {

	CandidateDTO saveCandidate(CandidateInDTO candidateInDTO) throws UserAvailableException;

	List<CandidateDTO> findAll();

	CandidateDTO updateCandidate(int userId, CandidateInDTO candidateInDTO);
}
