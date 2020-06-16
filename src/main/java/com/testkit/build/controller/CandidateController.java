package com.testkit.build.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testkit.build.dto.CandidateDTO;
import com.testkit.build.dto.CandidateInDTO;
import com.testkit.build.services.CandidateService;

@RestController()
@RequestMapping(value = "/candidate")
public class CandidateController {

	@Autowired
	CandidateService service;

	@PostMapping(value = "/addcandidate")
	public CandidateDTO addUser(@RequestBody CandidateInDTO candidateIndto) {
		return service.saveCandidate(candidateIndto);
	}

	@GetMapping(value = "/getcandidate")
	public List<CandidateDTO> getCandidate() {
		return service.findAll();
	}

	@PutMapping(value = "/updatecandidate/{userId}")
	public CandidateDTO updateAdmin(@RequestBody CandidateInDTO candidateInDTO, @PathVariable int userId) {
		return service.updateCandidate(userId, candidateInDTO);
	}

}
