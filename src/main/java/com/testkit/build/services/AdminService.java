package com.testkit.build.services;

import java.util.List;

import com.testkit.build.dto.AdminDTO;
import com.testkit.build.dto.AdminInDTO;

public interface AdminService {

	AdminDTO saveAdmin(AdminInDTO adminInDTO);

	List<AdminDTO> findAll();

	AdminDTO updateAdmin(int userId, AdminInDTO adminInDTO);

	boolean deleteAdmin(int userid);

}
