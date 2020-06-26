package com.testkit.build.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.testkit.build.common.dto.DeveloperMessage;
import com.testkit.build.common.dto.ErrorMessage;
import com.testkit.build.common.enums.ErrorCode;
import com.testkit.build.common.exception.BadRequestException;
import com.testkit.build.common.exception.NotFoundException;
import com.testkit.build.dao.ResourceRepository;
import com.testkit.build.dto.ResourceDTO;
import com.testkit.build.dto.ResourceInDTO;
import com.testkit.build.entity.ResourceEntity;
import com.testkit.build.mapper.ResourceMapper;
import com.testkit.build.predicates.ResourcePredicate;
import com.testkit.build.services.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	ResourceRepository resourceRepository;

	@Lazy
	@Autowired
	ResourceMapper mapper;

	@Override
	public ResourceDTO save(ResourceInDTO resourceInDTO) {
		validateResource(resourceInDTO);
		return this.createResourceDTO(resourceRepository.save(createResourceEntity(resourceInDTO)));

	}

	@Override
	public List<ResourceDTO> findAll() {
		List<ResourceEntity> entityList = new ArrayList<>();
		resourceRepository.findAll().forEach(entityList::add);
		if (entityList.isEmpty()) {
			throw new NotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION).addDeveloperMessage(
					new DeveloperMessage(ErrorCode.NOT_FOUND_EXCEPTION, "No user available in the database with ID")));
		}
		return getResourceDTOs(entityList);
	}

	@Override
	public ResourceDTO update(int userId, ResourceInDTO resourceInDTO) {
		ResourceEntity resourceEntity = find(userId);
		resourceEntity = updateResourceEntity(resourceInDTO, resourceEntity);
		ValidateResourceForSameEmailOrMobileWithDifferentId(resourceEntity);
		resourceEntity = resourceRepository.save(resourceEntity);
		return createResourceDTO(resourceEntity);
	}

	@Override
	public boolean delete(int userId) {
		findById(userId);
		resourceRepository.deleteById(userId);
		return true;
	}

	@Override
	public ResourceDTO findById(int userId) {
		return createResourceDTO(find(userId));
	}

	private ResourceEntity find(int userId) {
		ResourceEntity resourceEntity = null;
		Optional<ResourceEntity> optional = resourceRepository.findById(userId);
		if (optional.isPresent()) {
			resourceEntity = optional.get();
		} else {
			throw new NotFoundException(new ErrorMessage(ErrorCode.NOT_FOUND_EXCEPTION)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.NOT_FOUND_EXCEPTION,
							"No user available in the database with ID{" + userId + "}")));

		}
		return resourceEntity;
	}

	private Boolean ValidateResourceForSameEmailOrMobileWithDifferentId(ResourceEntity resourceEntity) {

		BooleanBuilder where = ResourcePredicate.userEmailEqOrMobileEqIdNeq(resourceEntity);
		Optional<ResourceEntity> optionalResurceEntity = resourceRepository.findOne(where);
		if (optionalResurceEntity.isPresent()) {
			throw new BadRequestException(new ErrorMessage(ErrorCode.BAD_REQUEST)
					.addDeveloperMessage(new DeveloperMessage(ErrorCode.USER_ALREADY_EXISTS,
							"Another entity with same email and mobile number present")));
		}

		return true;
	}

	private List<ResourceDTO> getResourceDTOs(List<ResourceEntity> entityList) {
		List<ResourceDTO> resDtos = new ArrayList<>();

		for (ResourceEntity resourceEntity : entityList) {
			resDtos.add(createResourceDTO(resourceEntity));
		}
		return resDtos;
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

			throw new BadRequestException(new ErrorMessage(ErrorCode.BAD_REQUEST).addDeveloperMessage(
					new DeveloperMessage(ErrorCode.USER_ALREADY_EXISTS, "User is already registered, try log-in")));

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
