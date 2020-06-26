package com.testkit.dataprovider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.testkit.build.dto.ResourceDTO;
import com.testkit.build.dto.ResourceInDTO;
import com.testkit.build.entity.ResourceEntity;

public class ResourceServiceDataProvider {

	@DataProvider
	public Object[][] getDataForUpdateResourceNotFoundException() {

		ResourceEntity resourceEntity = null;
		return new Object[][] { { 202, resourceEntity } };

	}

	@DataProvider
	public Object[][] getDataForSaveBadRequestException() {

		ResourceEntity outputResourceEntity = new ResourceEntity();
		outputResourceEntity.setRegistrationDate(new Date());
		outputResourceEntity.setId(2);
		outputResourceEntity.setUserEmail("abc@gmail.com");
		outputResourceEntity.setUserMobile("123456789");
		outputResourceEntity.setUserName("abc");
		outputResourceEntity.setUserPassword("password");

		ResourceInDTO resourceInDTO = new ResourceInDTO();
		resourceInDTO.setRegistrationDate(new Date());
		resourceInDTO.setUserEmail("abc@gmail.com");
		resourceInDTO.setUserMobile("123456789");
		resourceInDTO.setUserName("abc");
		resourceInDTO.setUserPassword("password");

		return new Object[][] { { resourceInDTO, outputResourceEntity } };

	}

	@DataProvider
	public Object[][] getDataForSaveSuccess() {

		ResourceEntity outputResourceEntity = new ResourceEntity();
		outputResourceEntity.setRegistrationDate(new Date());
		outputResourceEntity.setId(2);
		outputResourceEntity.setUserEmail("abc@gmail.com");
		outputResourceEntity.setUserMobile("123456789");
		outputResourceEntity.setUserName("abc");
		outputResourceEntity.setUserPassword("password");

		ResourceInDTO resourceInDTO = new ResourceInDTO();
		resourceInDTO.setRegistrationDate(new Date());
		resourceInDTO.setUserEmail("abc@gmail.com");
		resourceInDTO.setUserMobile("123456789");
		resourceInDTO.setUserName("abc");
		resourceInDTO.setUserPassword("password");

		ResourceDTO resourceDTO = new ResourceDTO();
		resourceDTO.setId(outputResourceEntity.getId());
		resourceDTO.setRegistrationDate(outputResourceEntity.getRegistrationDate());
		resourceDTO.setUserEmail(outputResourceEntity.getUserEmail());
		resourceDTO.setUserMobile(outputResourceEntity.getUserMobile());
		resourceDTO.setUserName(outputResourceEntity.getUserName());
		resourceDTO.setUserPassword(outputResourceEntity.getUserPassword());

		return new Object[][] { { resourceInDTO, outputResourceEntity, resourceDTO } };

	}

	@DataProvider
	public Object[][] getDataForUpdateResourceBadRequestException() {

		ResourceEntity inputResourceEntity = createResourceEntity();
		ResourceEntity outputResourceEntity = new ResourceEntity();
		outputResourceEntity.setRegistrationDate(new Date());
		outputResourceEntity.setId(2);
		outputResourceEntity.setUserEmail("abc@gmail.com");
		outputResourceEntity.setUserMobile("123456789");
		outputResourceEntity.setUserName("abc");
		outputResourceEntity.setUserPassword("password");
		return new Object[][] { { inputResourceEntity, outputResourceEntity } };

	}

	@DataProvider
	public Object[][] getDataForUpdateResourceSuccess() {

		ResourceEntity inputResourceEntity = new ResourceEntity();
		inputResourceEntity.setRegistrationDate(new Date());
		inputResourceEntity.setId(1);
		inputResourceEntity.setUserEmail("XYZ@@gmail.com");
		inputResourceEntity.setUserMobile("987654323");
		inputResourceEntity.setUserName("Naresh");
		inputResourceEntity.setUserPassword("password");

		ResourceDTO resourceDTO = new ResourceDTO();
		resourceDTO.setId(inputResourceEntity.getId());
		resourceDTO.setRegistrationDate(inputResourceEntity.getRegistrationDate());
		resourceDTO.setUserEmail(inputResourceEntity.getUserEmail());
		resourceDTO.setUserMobile(inputResourceEntity.getUserMobile());
		resourceDTO.setUserName(inputResourceEntity.getUserName());
		resourceDTO.setUserPassword(inputResourceEntity.getUserPassword());

		return new Object[][] { { 1, inputResourceEntity, resourceDTO } };

	}

	@DataProvider
	public Object[][] getDataForDeleteResourceNotFoundExceptino() {

		return new Object[][] { { 101 } };

	}

	@DataProvider
	public Object[][] getDataForDeleteResourceSuccess() {
		ResourceEntity inputResourceEntity = new ResourceEntity();
		inputResourceEntity.setRegistrationDate(new Date());
		inputResourceEntity.setId(1);
		inputResourceEntity.setUserEmail("XYZ@@gmail.com");
		inputResourceEntity.setUserMobile("987654323");
		inputResourceEntity.setUserName("Naresh");
		inputResourceEntity.setUserPassword("password");
		return new Object[][] { { 1, inputResourceEntity } };

	}

	@DataProvider
	public Object[][] getDataForFindAllSuccess() {
		List<ResourceEntity> resourceList = new ArrayList<>();
		resourceList.add(new ResourceEntity());
		resourceList.add(new ResourceEntity());

		return new Object[][] { { resourceList } };

	}

	private ResourceEntity createResourceEntity() {
		ResourceEntity resourceEntity = new ResourceEntity();
		resourceEntity.setRegistrationDate(new Date());
		resourceEntity.setId(1);
		resourceEntity.setUserEmail("abc@gmail.com");
		resourceEntity.setUserMobile("123456789");
		resourceEntity.setUserName("Naresh");
		resourceEntity.setUserPassword("password");
		return resourceEntity;
	}

	@DataProvider
	public Object[][] getDataForFindByIdSuccess() {

		ResourceEntity outputResourceEntity = new ResourceEntity();
		outputResourceEntity.setRegistrationDate(new Date());
		outputResourceEntity.setId(2);
		outputResourceEntity.setUserEmail("abc@gmail.com");
		outputResourceEntity.setUserMobile("123456789");
		outputResourceEntity.setUserName("abc");
		outputResourceEntity.setUserPassword("password");

		ResourceDTO resourceDTO = new ResourceDTO();
		resourceDTO.setRegistrationDate(new Date());
		resourceDTO.setId(2);
		resourceDTO.setUserEmail("abc@gmail.com");
		resourceDTO.setUserMobile("123456789");
		resourceDTO.setUserName("abc");
		resourceDTO.setUserPassword("password");

		return new Object[][] { { 2, outputResourceEntity, resourceDTO } };

	}
}
