package com.testkit.build.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.testkit.build.common.dto.DeveloperMessage;
import com.testkit.build.common.dto.ErrorMessage;
import com.testkit.build.common.enums.ErrorCode;
import com.testkit.build.common.exception.UserAvailableException;
import com.testkit.build.common.exception.UserNotFoundException;
import com.testkit.build.dao.AdminRepository;
import com.testkit.build.dto.AdminDTO;
import com.testkit.build.dto.AdminInDTO;
import com.testkit.build.entity.AdminEntity;
import com.testkit.build.entity.UserEntity;
import com.testkit.build.mapper.AdminMapper;
import com.testkit.build.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;

	@Lazy
	@Autowired
	AdminMapper mapper;

	@Override
	public AdminDTO saveAdmin(AdminInDTO adminInDTO) {
		validateAdmin(adminInDTO);
		return this.createAdminDTO(adminRepository.save(this.createAdminEntity(adminInDTO)));
	}

	@Override
	public List<AdminDTO> findAll() {
		List<AdminEntity> adminEntitylist = new ArrayList<>();
		this.adminRepository.findAll().forEach(adminEntitylist::add);
		if (adminEntitylist.isEmpty()) {
			throw new UserNotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION).addDeveloperMessage(
					new DeveloperMessage(ErrorCode.NOT_FOUND_EXCEPTION, "No users available in the database")));
		}
		return createAdminDTOs(adminEntitylist);
	}

	@Override
	public AdminDTO updateAdmin(int userId, AdminInDTO adminInDTO) {
		AdminEntity adminEntity = null;
		Optional<AdminEntity> optionalAdminEntity = adminRepository.findById(userId);

		if (!optionalAdminEntity.isPresent()) {
			throw new UserNotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.NOT_FOUND_EXCEPTION,
							"No user available in the database with ID{" + userId + "}")));
		}

		adminEntity = optionalAdminEntity.get();
		adminEntity = updateAdminEntity(adminInDTO, adminEntity);
		adminEntity = adminRepository.save(adminEntity);

		return this.createAdminDTO(adminEntity);

	}

	@Override
	public boolean deleteAdmin(int userid) {
		if (findAdminEntityById(userid) != null) {
			throw new UserNotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.NOT_FOUND_EXCEPTION,
							"No user available in the database with ID{" + userid + "}")));
		}
		adminRepository.deleteById(userid);
		return true;
	}

	private AdminEntity findAdminEntityById(int userid) {
		AdminEntity adminEntity = null;
		Optional<AdminEntity> optional = adminRepository.findById(userid);
		if (optional.isPresent()) {
			adminEntity = optional.get();
		}
		return adminEntity;
	}

	private List<AdminDTO> createAdminDTOs(List<AdminEntity> adminEntitylist) {
		List<AdminDTO> adminDTOs = new ArrayList<AdminDTO>();
		for (AdminEntity adminEntity : adminEntitylist) {
			adminDTOs.add(createAdminDTO(adminEntity));
		}
		return adminDTOs;
	}

	private UserEntity findUserByUserEmailOrUserMobile(String userEmail, String userMobile) {
		return adminRepository.findAdminByUserEmailOrUserMobile(userEmail, userMobile);
	}

	private AdminEntity createAdminEntity(AdminInDTO adminInDTO) {
		return mapper.AdminInDTOToAdmin(adminInDTO);
	}

	private AdminDTO createAdminDTO(AdminEntity adminEntity) {
		return mapper.AdminEntityTOAdminDTO(adminEntity);
	}

	private boolean validateAdmin(AdminInDTO adminInDTO) {

		AdminEntity adminEntity = (AdminEntity) findUserByUserEmailOrUserMobile(adminInDTO.getUserEmail(),
				adminInDTO.getUserMobile());
		if (adminEntity != null) {

			throw new UserAvailableException(new ErrorMessage(ErrorCode.BAD_REQUEST).addDeveloperMessage(
					new DeveloperMessage(ErrorCode.USER_ALREADY_EXISTS, "User is already registered, try log-in")));

		}

		return true;
	}

	private AdminEntity updateAdminEntity(AdminInDTO adminInDTO, AdminEntity adminEntity) {
		return mapper.adminInDTOToAdminEntity(adminInDTO, adminEntity);
	}

}
