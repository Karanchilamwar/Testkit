package com.testkit.build.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testkit.build.dao.UserRepository;
import com.testkit.build.entity.UserEntity;
import com.testkit.build.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<UserEntity> findAll() {
		List<UserEntity> list = new ArrayList<>();
		userRepository.findAll().forEach(list::add);
		return list;
	}

}
