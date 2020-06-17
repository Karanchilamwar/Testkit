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
<<<<<<< HEAD
import com.testkit.build.common.exception.UserNotFoundException;
=======
>>>>>>> master
import com.testkit.build.dao.ResourceRepository;
import com.testkit.build.dto.ResourceDTO;
import com.testkit.build.dto.ResourceInDTO;
import com.testkit.build.entity.ResourceEntity;
import com.testkit.build.mapper.ResourceMapper;
import com.testkit.build.services.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	ResourceRepository resourceRepository;

	@Lazy
	@Autowired
	ResourceMapper mapper;

	@Override
	public ResourceDTO saveResource(ResourceInDTO resourceInDTO) {
		validateResource(resourceInDTO);
		return this.createResourceDTO(resourceRepository.save(createResourceEntity(resourceInDTO)));

	}

	@Override
	public List<ResourceDTO> findAll() {
		List<ResourceEntity> entityList = new ArrayList<>();
		resourceRepository.findAll().forEach(entityList::add);
		if (entityList.isEmpty()) {
			throw new UserNotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION).addDeveloperMessage(
					new DeveloperMessage(ErrorCode.NOT_FOUND_EXCEPTION, "No user available in the database with ID")));
		}
		return getResourceDTOs(entityList);
	}

	@Override
	public ResourceDTO updateResource(int userId, ResourceInDTO resourceInDTO) {
		ResourceDTO resourceDTO = null;
		Optional<ResourceEntity> optionalResourceEntity = resourceRepository.findById(userId);
		if (!optionalResourceEntity.isPresent()) {
			throw new UserNotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.NOT_FOUND_EXCEPTION,
							"No user available in the database with ID{" + userId + "}")));
		}
		ResourceEntity resourceEntity = optionalResourceEntity.get();
		resourceEntity = updateResourceEntity(resourceInDTO, resourceEntity);
		resourceEntity = resourceRepository.save(resourceEntity);
		resourceDTO = createResourceDTO(resourceEntity);

		return resourceDTO;
	}

	@Override
	public boolean deleteResource(int userid) {
		if (getResourceEntityById(userid) != null) {
			throw new UserNotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.NOT_FOUND_EXCEPTION,
							"No user available in the database with ID{" + userid + "}")));
		}
		resourceRepository.deleteById(userid);
		return true;
	}

	private List<ResourceDTO> getResourceDTOs(List<ResourceEntity> entityList) {
		List<ResourceDTO> resDtos = new ArrayList<>();

		for (ResourceEntity resourceEntity : entityList) {
			resDtos.add(createResourceDTO(resourceEntity));
		}
		return resDtos;
	}

	private ResourceEntity getResourceEntityById(int userid) {
		ResourceEntity resourceEntity = null;
		Optional<ResourceEntity> optional = resourceRepository.findById(userid);
		if (optional.isPresent()) {
			resourceEntity = optional.get();
		}
		return resourceEntity;
	}

	private ResourceEntity updateResourceEntity(ResourceInDTO resourceInDTO, ResourceEntity resourceEntity) {
		return mapper.resourceInDTOToResourceEntity(resourceInDTO, resourceEntity);
	}

	private ResourceEntity findUserByUserEmailOrUserMobile(String userEmail, String userMobile) {
		return resourceRepository.findResourceEntityByUserEmailOrUserMobile(userEmail, userMobile);
	}

	private boolean validateResource(ResourceInDTO resourceInDTO) {
		ResourceEntity resourceEntity = this.findUserByUserEmailOrUserMobile(resourceInDTO.getUserEmail(),
				resourceInDTO.getUserMobile());
		if (resourceEntity != null) {
<<<<<<< HEAD
			throw new UserAvailableException(new ErrorMessage(ErrorCode.BAD_REQUEST).addDeveloperMessage(
					new DeveloperMessage(ErrorCode.USER_ALREADY_EXISTS, "User is already registered, try log-in")));
=======
			throw new UserAvailableException(new ErrorMessage(ErrorCode.USER_ALREADY_EXISTS)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.USER_ALREADY_EXISTS)));
>>>>>>> master
		}

		return true;
	}

	private ResourceEntity createResourceEntity(ResourceInDTO resourceInDTO) {
		return mapper.ResourceInDTOToResourceEntity(resourceInDTO);
	}

	private ResourceDTO createResourceDTO(ResourceEntity resourceEntity) {
		return mapper.ResourceEntityTOResourceDTO(resourceEntity);
	}

}
