package com.testkit.build.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.testkit.build.dao.AdminRepository;
import com.testkit.build.dto.AdminDTO;
import com.testkit.build.dto.AdminInDTO;
import com.testkit.build.entity.AdminEntity;
import com.testkit.build.entity.UserEntity;
import com.testkit.build.exception.UserAvailableException;
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
		return createAdminDTOs(adminEntitylist);
	}

	@Override
	public AdminDTO updateAdmin(int userId, AdminInDTO adminInDTO) {
		AdminEntity adminEntity = null;
		Optional<AdminEntity> optionalAdminEntity = adminRepository.findById(userId);

		if (optionalAdminEntity.isPresent()) {
			adminEntity = optionalAdminEntity.get();
			adminEntity = updateAdminEntity(adminInDTO, adminEntity);
			adminEntity = adminRepository.save(adminEntity);
		}
		return this.createAdminDTO(adminEntity);

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
			try {
				throw new UserAvailableException();
			} catch (UserAvailableException e) {

			}

		}

		return true;
	}

	private AdminEntity updateAdminEntity(AdminInDTO adminInDTO, AdminEntity adminEntity) {
		return mapper.adminInDTOToAdminEntity(adminInDTO, adminEntity);
	}

}
