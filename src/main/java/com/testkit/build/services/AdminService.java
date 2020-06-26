package com.testkit.build.services;

import java.util.List;

import com.testkit.build.dto.AdminDTO;
import com.testkit.build.dto.AdminInDTO;

public interface AdminService {

	AdminDTO save(AdminInDTO adminInDTO);

	List<AdminDTO> findAll();

	AdminDTO findById(int adminId);

	AdminDTO update(int userId, AdminInDTO adminInDTO);

	boolean delete(int userid);
}
