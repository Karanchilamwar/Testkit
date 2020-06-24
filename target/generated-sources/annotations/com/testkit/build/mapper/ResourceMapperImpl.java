package com.testkit.build.mapper;

import com.testkit.build.dto.ResourceDTO;
import com.testkit.build.dto.ResourceInDTO;
import com.testkit.build.entity.ResourceEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-23T15:45:04+0530",
    comments = "version: 1.4.0.Beta1, compiler: Eclipse JDT (IDE) 3.21.0.v20200304-1404, environment: Java 1.8.0_251 (Oracle Corporation)"
)
@Component
public class ResourceMapperImpl implements ResourceMapper {

    @Override
    public ResourceEntity ResourceInDTOToResourceEntity(ResourceInDTO resourceInDTO) {
        if ( resourceInDTO == null ) {
            return null;
        }

        ResourceEntity resourceEntity = new ResourceEntity();

        resourceEntity.setUserName( resourceInDTO.getUserName() );
        resourceEntity.setUserEmail( resourceInDTO.getUserEmail() );
        resourceEntity.setUserPassword( resourceInDTO.getUserPassword() );
        resourceEntity.setUserMobile( resourceInDTO.getUserMobile() );
        resourceEntity.setRegistrationDate( resourceInDTO.getRegistrationDate() );

        return resourceEntity;
    }

    @Override
    public ResourceDTO ResourceEntityTOResourceDTO(ResourceEntity resourceEntity) {
        if ( resourceEntity == null ) {
            return null;
        }

        ResourceDTO resourceDTO = new ResourceDTO();

        resourceDTO.setUserName( resourceEntity.getUserName() );
        resourceDTO.setUserEmail( resourceEntity.getUserEmail() );
        resourceDTO.setUserMobile( resourceEntity.getUserMobile() );
        resourceDTO.setRegistrationDate( resourceEntity.getRegistrationDate() );
        resourceDTO.setId( resourceEntity.getId() );
        resourceDTO.setUserPassword( resourceEntity.getUserPassword() );

        return resourceDTO;
    }

    @Override
    public ResourceEntity resourceInDTOToResourceEntity(ResourceInDTO resourceInDTO, ResourceEntity resourceEntity) {
        if ( resourceInDTO == null ) {
            return null;
        }

        resourceEntity.setUserEmail( resourceInDTO.getUserEmail() );
        resourceEntity.setUserMobile( resourceInDTO.getUserMobile() );
        resourceEntity.setUserName( resourceInDTO.getUserName() );
        resourceEntity.setUserPassword( resourceInDTO.getUserPassword() );
        resourceEntity.setRegistrationDate( resourceInDTO.getRegistrationDate() );

        return resourceEntity;
    }
}
