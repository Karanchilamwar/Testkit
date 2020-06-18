package com.testkit.build.mapper;

import com.testkit.build.dto.CandidateDTO;
import com.testkit.build.dto.CandidateInDTO;
import com.testkit.build.entity.CandidateEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-18T11:59:29+0530",
    comments = "version: 1.4.0.Beta1, compiler: Eclipse JDT (IDE) 3.21.0.v20200304-1404, environment: Java 1.8.0_251 (Oracle Corporation)"
)
@Component
public class CandidateMapperImpl implements CandidateMapper {

    @Override
    public CandidateEntity CandidateInDTOToCandidateEntity(CandidateInDTO candidateInDTO) {
        if ( candidateInDTO == null ) {
            return null;
        }

        CandidateEntity candidateEntity = new CandidateEntity();

        candidateEntity.setUserName( candidateInDTO.getUserName() );
        candidateEntity.setUserEmail( candidateInDTO.getUserEmail() );
        candidateEntity.setUserPassword( candidateInDTO.getUserPassword() );
        candidateEntity.setUserMobile( candidateInDTO.getUserMobile() );
        candidateEntity.setRegistrationDate( candidateInDTO.getRegistrationDate() );

        return candidateEntity;
    }

    @Override
    public CandidateDTO CandidateEntityTOCandidateDTO(CandidateEntity candidateEntity) {
        if ( candidateEntity == null ) {
            return null;
        }

        CandidateDTO candidateDTO = new CandidateDTO();

        candidateDTO.setUserName( candidateEntity.getUserName() );
        candidateDTO.setUserEmail( candidateEntity.getUserEmail() );
        candidateDTO.setUserMobile( candidateEntity.getUserMobile() );
        candidateDTO.setRegistrationDate( candidateEntity.getRegistrationDate() );
        candidateDTO.setId( candidateEntity.getId() );
        candidateDTO.setUserPassword( candidateEntity.getUserPassword() );

        return candidateDTO;
    }

    @Override
    public CandidateEntity CandidateInDTOToCandidateEntity(CandidateInDTO candidateInDTO, CandidateEntity candidateEntity) {
        if ( candidateInDTO == null ) {
            return null;
        }

        candidateEntity.setUserEmail( candidateInDTO.getUserEmail() );
        candidateEntity.setUserMobile( candidateInDTO.getUserMobile() );
        candidateEntity.setUserName( candidateInDTO.getUserName() );
        candidateEntity.setUserPassword( candidateInDTO.getUserPassword() );
        candidateEntity.setRegistrationDate( candidateInDTO.getRegistrationDate() );

        return candidateEntity;
    }
}
