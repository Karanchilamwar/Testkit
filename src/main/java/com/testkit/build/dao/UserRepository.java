package com.testkit.build.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testkit.build.entity.AdminEntity;
import com.testkit.build.entity.UserEntity;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity save(AdminEntity adminEntity);

	AdminEntity findAdminByUserEmailOrUserMobile(String userEmail, String userMobile);

}
