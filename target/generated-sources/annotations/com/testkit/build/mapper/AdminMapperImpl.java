package com.testkit.build.mapper;

import com.testkit.build.dto.AdminDTO;
import com.testkit.build.dto.AdminInDTO;
import com.testkit.build.entity.AdminEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-23T15:45:04+0530",
    comments = "version: 1.4.0.Beta1, compiler: Eclipse JDT (IDE) 3.21.0.v20200304-1404, environment: Java 1.8.0_251 (Oracle Corporation)"
)
@Component
public class AdminMapperImpl implements AdminMapper {

    @Override
    public AdminEntity AdminInDTOToAdmin(AdminInDTO adminInDTO) {
        if ( adminInDTO == null ) {
            return null;
        }

        AdminEntity adminEntity = new AdminEntity();

        adminEntity.setUserName( adminInDTO.getUserName() );
        adminEntity.setUserEmail( adminInDTO.getUserEmail() );
        adminEntity.setUserPassword( adminInDTO.getUserPassword() );
        adminEntity.setUserMobile( adminInDTO.getUserMobile() );
        adminEntity.setAdminLevel( adminInDTO.getAdminLevel() );

        return adminEntity;
    }

    @Override
    public AdminDTO AdminEntityTOAdminDTO(AdminEntity adminEntity) {
        if ( adminEntity == null ) {
            return null;
        }

        AdminDTO adminDTO = new AdminDTO();

        adminDTO.setUserName( adminEntity.getUserName() );
        adminDTO.setUserEmail( adminEntity.getUserEmail() );
        adminDTO.setUserMobile( adminEntity.getUserMobile() );
        adminDTO.setAdminLevel( adminEntity.getAdminLevel() );
        adminDTO.setId( adminEntity.getId() );
        adminDTO.setUserPassword( adminEntity.getUserPassword() );

        return adminDTO;
    }

    @Override
    public AdminEntity adminInDTOToAdminEntity(AdminInDTO adminInDTO, AdminEntity adminEntity) {
        if ( adminInDTO == null ) {
            return null;
        }

        adminEntity.setUserEmail( adminInDTO.getUserEmail() );
        adminEntity.setUserMobile( adminInDTO.getUserMobile() );
        adminEntity.setUserName( adminInDTO.getUserName() );
        adminEntity.setUserPassword( adminInDTO.getUserPassword() );
        adminEntity.setAdminLevel( adminInDTO.getAdminLevel() );

        return adminEntity;
    }
}
