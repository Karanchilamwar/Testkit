package com.testkit.build.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.testkit.build.dao.ResourceRepository;
import com.testkit.build.dto.ResourceDTO;
import com.testkit.build.dto.ResourceInDTO;
import com.testkit.build.entity.ResourceEntity;
import com.testkit.build.exception.UserAvailableException;
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
	public ResourceDTO saveResource(ResourceInDTO resourceInDTO) throws UserAvailableException {
		validateResource(resourceInDTO);
		return this.createResourceDTO((ResourceEntity) resourceRepository.save(createResourceEntity(resourceInDTO)));

	}

	@Override
	public List<ResourceDTO> findAll() {
		List<ResourceEntity> entityList = new ArrayList<>();
		resourceRepository.findAll().forEach(entityList::add);
		return getResourceDTOs(entityList);
	}

	public List<ResourceDTO> getResourceDTOs(List<ResourceEntity> entityList) {
		List<ResourceDTO> resDtos = new ArrayList<>();

		for (ResourceEntity resourceEntity : entityList) {
			resDtos.add(createResourceDTO(resourceEntity));
		}
		return resDtos;
	}

	@Override
	public ResourceDTO updateResource(int userId, ResourceInDTO resourceInDTO) {
		ResourceDTO resourceDTO = null;
		Optional<ResourceEntity> optionalResourceEntity = resourceRepository.findById(userId);
		if (optionalResourceEntity.isPresent()) {
			ResourceEntity resourceEntity = optionalResourceEntity.get();
			resourceEntity = updateResourceEntity(resourceInDTO, resourceEntity);
			resourceEntity = resourceRepository.save(resourceEntity);
			resourceDTO = createResourceDTO(resourceEntity);
		}
		return resourceDTO;
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
			try {
				throw new UserAvailableException();
			} catch (UserAvailableException availableException) {

			}
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
