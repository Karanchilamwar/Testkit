package com.testkit.build.services;

import java.util.List;

import com.testkit.build.dto.CandidateDTO;
import com.testkit.build.dto.CandidateInDTO;

public interface CandidateService {

	CandidateDTO saveCandidate(CandidateInDTO candidateInDTO);

	List<CandidateDTO> findAll();

	CandidateDTO updateCandidate(int userId, CandidateInDTO candidateInDTO);

	boolean deleteCandidate(int userid);
}
