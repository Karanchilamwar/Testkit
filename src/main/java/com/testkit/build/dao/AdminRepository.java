package com.testkit.build.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.testkit.build.entity.AdminEntity;

@Repository("adminRepository")
public interface AdminRepository extends JpaRepository<AdminEntity, Integer>, QuerydslPredicateExecutor<AdminEntity> {

	@Override
	AdminEntity save(AdminEntity adminEntity);

	AdminEntity findAdminByUserEmailOrUserMobile(String userEmail, String userMobile);

}
