package com.testkit.build.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.testkit.build.common.dto.DeveloperMessage;
import com.testkit.build.common.dto.ErrorMessage;
import com.testkit.build.common.enums.ErrorCode;
import com.testkit.build.common.exception.BadRequestException;
import com.testkit.build.common.exception.NotFoundException;
import com.testkit.build.dao.AdminRepository;
import com.testkit.build.dto.AdminDTO;
import com.testkit.build.dto.AdminInDTO;
import com.testkit.build.entity.AdminEntity;
import com.testkit.build.entity.QAdminEntity;
import com.testkit.build.entity.UserEntity;
import com.testkit.build.mapper.AdminMapper;
import com.testkit.build.predicates.AdminPredicate;
import com.testkit.build.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;

	@Lazy
	@Autowired
	AdminMapper mapper;

	@Override
	public AdminDTO save(AdminInDTO adminInDTO) {
		validateAdmin(adminInDTO);
		return this.createAdminDTO(adminRepository.save(this.createAdminEntity(adminInDTO)));
	}

	@Override
	public List<AdminDTO> findAll() {
		List<AdminEntity> adminEntitylist = new ArrayList<>();
		this.adminRepository.findAll().forEach(adminEntitylist::add);
		if (adminEntitylist.isEmpty()) {
			throw new NotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION).addDeveloperMessage(
					new DeveloperMessage(ErrorCode.NOT_FOUND_EXCEPTION, "No users available in the database")));
		}
		return createAdminDTOs(adminEntitylist);
	}

	@Override
	public AdminDTO update(int userId, AdminInDTO adminInDTO) {
		AdminEntity adminEntity = null;
		adminEntity = find(userId);
		adminEntity = updateAdminEntity(adminInDTO, adminEntity);
		adminEntity = adminRepository.save(adminEntity);

		return this.createAdminDTO(adminEntity);

	}

	@Override
	public AdminDTO findById(int adminId) {
		return createAdminDTO(this.find(adminId));
	}

	@Override
	public boolean delete(int userid) {
		findAdminEntityById(userid);
		adminRepository.deleteById(userid);
		return true;
	}

	private AdminEntity find(int userId) {
		AdminEntity adminEntity;
		Optional<AdminEntity> optionalAdminEntity = adminRepository.findById(userId);

		if (!optionalAdminEntity.isPresent()) {
			throw new NotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.NOT_FOUND_EXCEPTION,
							"No user available in the database with ID{" + userId + "}")));
		}

		adminEntity = optionalAdminEntity.get();
		return adminEntity;
	}

	private AdminEntity findAdminEntityById(int userId) {
		AdminEntity adminEntity = null;
		Optional<AdminEntity> optionalAdminEntity = null;
		QAdminEntity qAdminEntity = QAdminEntity.adminEntity;
		BooleanExpression booleanExpression = AdminPredicate.userIdEq(userId);
		optionalAdminEntity = adminRepository.findOne(booleanExpression);
		if (optionalAdminEntity.isPresent()) {
			adminEntity = optionalAdminEntity.get();
		} else {
			throw new NotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.NOT_FOUND_EXCEPTION,
							"No user available in the database with ID{" + userId + "}")));
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

			throw new BadRequestException(new ErrorMessage(ErrorCode.BAD_REQUEST).addDeveloperMessage(
					new DeveloperMessage(ErrorCode.USER_ALREADY_EXISTS, "User is already registered, try log-in")));

		}

		return true;
	}

	private AdminEntity updateAdminEntity(AdminInDTO adminInDTO, AdminEntity adminEntity) {
		return mapper.adminInDTOToAdminEntity(adminInDTO, adminEntity);
	}

}
