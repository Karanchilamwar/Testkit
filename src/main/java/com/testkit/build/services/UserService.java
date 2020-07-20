package com.testkit.build.services;

import java.util.List;

import com.testkit.build.entity.UserEntity;

public interface UserService {

	List<UserEntity> findAll();

	UserEntity findById(int id);

}
