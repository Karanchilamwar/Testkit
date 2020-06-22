package com.testkit.build.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testkit.build.common.dto.DeveloperMessage;
import com.testkit.build.common.dto.ErrorMessage;
import com.testkit.build.common.enums.ErrorCode;
import com.testkit.build.common.exception.NotFoundException;
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
		if (list.isEmpty()) {
			throw new NotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION).addDeveloperMessage(
					new DeveloperMessage(ErrorCode.NOT_FOUND_EXCEPTION, "No users available in the database")));

		}
		return list;
	}

}
