package com.testkit.servicetest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testkit.build.common.enums.ErrorCode;
import com.testkit.build.common.exception.BadRequestException;
import com.testkit.build.common.exception.NotFoundException;
import com.testkit.build.dao.ResourceRepository;
import com.testkit.build.dto.ResourceDTO;
import com.testkit.build.dto.ResourceInDTO;
import com.testkit.build.entity.ResourceEntity;
import com.testkit.build.mapper.ResourceMapper;
import com.testkit.build.mapper.ResourceMapperImpl;
import com.testkit.build.predicates.ResourcePredicate;
import com.testkit.build.serviceImpl.ResourceServiceImpl;
import com.testkit.build.services.ResourceService;
import com.testkit.dataprovider.ResourceServiceDataProvider;

public class ResourceServiceTest {

	@Mock
	private ResourceRepository resourceRepository;

	@Mock
	private ResourceMapper mapper;

	@Mock
	private ResourcePredicate resourcePredicate;

	@InjectMocks
	private ResourceService resourceService;

	@BeforeMethod
	public void setUp() {
		resourceService = new ResourceServiceImpl();
		mapper = new ResourceMapperImpl();
		MockitoAnnotations.initMocks(this);
	}

	@Test(dataProvider = "getDataForSaveBadRequestException", dataProviderClass = ResourceServiceDataProvider.class)
	public void RST_01_shouldThrowBRE_saveResource(ResourceInDTO resourceInDTO, ResourceEntity outResourceEntity) {
		when(resourceRepository.findResourceEntityByUserEmailOrUserMobile(resourceInDTO.getUserEmail(),
				resourceInDTO.getUserMobile())).thenReturn(outResourceEntity);

		try {
			resourceService.saveResource(resourceInDTO);
			fail("Bab request exception should throw");
		} catch (BadRequestException badRequest) {
			assertEquals(badRequest.getErrorMessage().getCode(), ErrorCode.BAD_REQUEST.getCode());
		}
	}

	@Test(dataProvider = "getDataForSaveSuccess", dataProviderClass = ResourceServiceDataProvider.class)
	public void RST_02_shouldSuccess_saveResource(ResourceInDTO resourceInDTO, ResourceEntity outResourceEntity,
			ResourceDTO ExpeResourceDTO) {

		when(resourceRepository.findResourceEntityByUserEmailOrUserMobile(resourceInDTO.getUserEmail(),
				resourceInDTO.getUserMobile())).thenReturn(null);

		when(mapper.ResourceInDTOToResourceEntity(resourceInDTO)).thenReturn(outResourceEntity);

		when(mapper.ResourceEntityTOResourceDTO(outResourceEntity)).thenReturn(ExpeResourceDTO);

		when(resourceRepository.save(outResourceEntity)).thenReturn(outResourceEntity);

		ResourceDTO resourceDTO2 = resourceService.saveResource(resourceInDTO);
		assertEquals(resourceDTO2, ExpeResourceDTO);
	}

	@Test(dataProvider = "getDataForUpdateResourceNotFoundException", dataProviderClass = ResourceServiceDataProvider.class)
	public void RST_03_shouldThrow_NFE_UpdateResource_userNotfoundError(int userId, ResourceEntity resourceEntity) {

		when(resourceRepository.findOne(resourcePredicate.userIdEq(userId)))
				.thenReturn(Optional.ofNullable(resourceEntity));
		try {
			resourceService.updateResource(userId, null);
			fail("should throw Not found Exception");
		} catch (NotFoundException notFoundException) {
			assertEquals(notFoundException.getErrorMessage().getCode(), ErrorCode.NOT_FOUND_EXCEPTION.getCode());
		}

	}

	@Test(dataProvider = "getDataForUpdateResourceBadRequestException", dataProviderClass = ResourceServiceDataProvider.class)
	public void RST_04_shouldThrow_BRE_UpdateResource_BadRequestError(ResourceEntity inResourceEntity,
			ResourceEntity outResourceEntity) {

		when(resourceRepository.findOne(resourcePredicate.userIdEq(inResourceEntity.getId())))
				.thenReturn(Optional.of(inResourceEntity));

		when(mapper.resourceInDTOToResourceEntity(null, inResourceEntity)).thenReturn(inResourceEntity);

		when(resourceRepository.findOne(ResourcePredicate.userEmailEqOrMobileEqIdNeq(inResourceEntity)))
				.thenReturn(Optional.of(outResourceEntity));

		try {
			resourceService.updateResource(1, null);
			fail("should throw bad request Exception");
		} catch (BadRequestException badRequestException) {
			assertEquals(badRequestException.getErrorMessage().getCode(), ErrorCode.BAD_REQUEST.getCode());
		}

	}

	@Test(dataProvider = "getDataForUpdateResourceSuccess", dataProviderClass = ResourceServiceDataProvider.class)
	public void RST_05_ShouldRunSuccess_UpdateResource(int id, ResourceEntity resourceEntity, ResourceDTO resourceDTO) {

		when(resourceRepository.findOne(resourcePredicate.userIdEq(id))).thenReturn(Optional.of(resourceEntity));

		when(mapper.resourceInDTOToResourceEntity(null, resourceEntity)).thenReturn(resourceEntity);

		when(resourceRepository.findOne(ResourcePredicate.userEmailEqOrMobileEqIdNeq(resourceEntity)))
				.thenReturn(Optional.empty());

		when(resourceRepository.save(resourceEntity)).thenReturn(resourceEntity);

		when(mapper.ResourceEntityTOResourceDTO(resourceEntity)).thenReturn(resourceDTO);

		ResourceDTO resourceDTO2 = resourceService.updateResource(id, null);

		assertEquals(resourceDTO2, resourceDTO);

	}

	@Test(dataProvider = "getDataForDeleteResourceNotFoundExceptino", dataProviderClass = ResourceServiceDataProvider.class)
	public void RST_06_shouldThrowNFE_deleteResource(int userId) {
		when(resourceRepository.findOne(ResourcePredicate.userIdEq(userId))).thenReturn(Optional.empty());
		try {
			resourceService.deleteResource(userId);
			fail("Not Found exception should throw");
		} catch (NotFoundException notFoundException) {
			assertEquals(notFoundException.getErrorMessage().getCode(), ErrorCode.NOT_FOUND_EXCEPTION.getCode());
		}
	}

	@Test(dataProvider = "getDataForDeleteResourceSuccess", dataProviderClass = ResourceServiceDataProvider.class)
	public void RST_07_shouldSuccess_deleteResource(int userId, ResourceEntity resourceEntity) {
		when(resourceRepository.findOne(ResourcePredicate.userIdEq(userId))).thenReturn(Optional.of(resourceEntity));
		boolean result = resourceService.deleteResource(userId);
		Mockito.verify(resourceRepository, times(1)).deleteById(userId);
	}

	@Test()
	public void RST_08_shouldThrowNFE_findAll() {
		when(resourceRepository.findAll()).thenReturn(new ArrayList<>());
		try {
			resourceService.findAll();
			fail("should throw Not found Exception");
		} catch (NotFoundException notFoundException) {
			assertEquals(notFoundException.getErrorMessage().getCode(), ErrorCode.NOT_FOUND_EXCEPTION.getCode());
		}

	}

	@Test(dataProvider = "getDataForFindAllSuccess", dataProviderClass = ResourceServiceDataProvider.class)
	public void RST_9_shouldSuccess_findAll(List<ResourceEntity> resourceList) {
		when(resourceRepository.findAll()).thenReturn(resourceList);

		List<ResourceDTO> resourceDTOs = resourceService.findAll();

		assertEquals(resourceDTOs.size(), 2);

	}

	@Test()
	public void RST_10_shouldThrowNFE_getResourceEntityById() {
		when(resourceRepository.findById(1001)).thenReturn(Optional.empty());
		try {
			resourceService.getResourceEntityById(1001);
			fail("should throw Not found Exception");
		} catch (NotFoundException notFoundException) {
			assertEquals(notFoundException.getErrorMessage().getCode(), ErrorCode.NOT_FOUND_EXCEPTION.getCode());
		}

	}

	@Test(dataProvider = "getDataForFindByIdSuccess", dataProviderClass = ResourceServiceDataProvider.class)
	public void RST_11_shouldSuccess_getResourceEntityById(int userID, ResourceEntity resourceEntity) {
		when(resourceRepository.findOne(ResourcePredicate.userIdEq(userID))).thenReturn(Optional.of(resourceEntity));

		ResourceEntity resourceEntityResult = resourceService.getResourceEntityById(userID);

		assertEquals(resourceEntityResult.getId(), userID);

	}

}
