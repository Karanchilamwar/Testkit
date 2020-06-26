package com.testkit.build.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testkit.build.dto.CandidateDTO;
import com.testkit.build.dto.CandidateInDTO;
import com.testkit.build.serviceImpl.CandidateServiceImpl;

@RestController()
@RequestMapping(value = "/candidates")
public class CandidateController {

	@Autowired
	CandidateServiceImpl service;

	@PostMapping
	public CandidateDTO addUser(@RequestBody CandidateInDTO candidateIndto) {
		return service.save(candidateIndto);
	}

	@GetMapping
	public List<CandidateDTO> getCandidates() {
		return service.findAll();
	}

	@GetMapping(value = "/{candidateId}")
	public CandidateDTO getCandidate(@PathVariable int candidateId) {
		return service.findById(candidateId);
	}

	@PutMapping(value = "/{userId}")
	public CandidateDTO updateAdmin(@RequestBody CandidateInDTO candidateInDTO, @PathVariable int userId) {
		return service.update(userId, candidateInDTO);
	}

	@DeleteMapping(value = "{userId}")
	public boolean deleteCandidate(@PathVariable int userId) {
		return service.delete(userId);
	}

}
