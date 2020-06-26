package com.testkit.build.services;

import java.util.List;

import com.testkit.build.dto.CandidateDTO;
import com.testkit.build.dto.CandidateInDTO;

public interface CandidateService {

	CandidateDTO save(CandidateInDTO candidateInDTO);

	List<CandidateDTO> findAll();

	CandidateDTO findById(int candidateId);

	CandidateDTO update(int userId, CandidateInDTO candidateInDTO);

	boolean delete(int userid);
}
