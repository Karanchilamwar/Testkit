package com.testkit.build.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.testkit.build.entity.CandidateEntity;

@Repository("CandidateRepository")
public interface CandidateRepository
		extends JpaRepository<CandidateEntity, Integer>, QuerydslPredicateExecutor<CandidateEntity> {

	@Override
	CandidateEntity save(CandidateEntity candidateEntity);

	CandidateEntity findCandidateEntityByUserEmailOrUserMobile(String userEmail, String userMobile);

}
