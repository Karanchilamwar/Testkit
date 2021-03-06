package com.testkit.build.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testkit.build.entity.CandidateEntity;

@Repository("CandidateRepository")
public interface CandidateRepository extends JpaRepository<CandidateEntity, Integer> {

	CandidateEntity save(CandidateEntity candidateEntity);

	CandidateEntity findCandidateEntityByUserEmailOrUserMobile(String userEmail, String userMobile);

}
