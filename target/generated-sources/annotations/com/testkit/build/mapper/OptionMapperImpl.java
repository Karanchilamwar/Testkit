package com.testkit.build.mapper;

import com.testkit.build.dto.OptionDTO;
import com.testkit.build.dto.OptionInDTO;
import com.testkit.build.dto.OptionUpdateDTO;
import com.testkit.build.entity.OptionEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-17T10:58:28+0530",
    comments = "version: 1.4.0.Beta1, compiler: Eclipse JDT (IDE) 3.21.0.v20200304-1404, environment: Java 1.8.0_251 (Oracle Corporation)"
)
@Component
public class OptionMapperImpl implements OptionMapper {

    @Override
    public OptionEntity optionInDTOToOptionEntity(OptionInDTO optionInDTO) {
        if ( optionInDTO == null ) {
            return null;
        }

        OptionEntity optionEntity = new OptionEntity();

        optionEntity.setOptionText( optionInDTO.getOptionText() );

        return optionEntity;
    }

    @Override
    public OptionDTO optionEntityToOptionDTO(OptionEntity optionEntity) {
        if ( optionEntity == null ) {
            return null;
        }

        OptionDTO optionDTO = new OptionDTO();

        optionDTO.setId( optionEntity.getId() );
        optionDTO.setOptionText( optionEntity.getOptionText() );

        return optionDTO;
    }

    @Override
    public OptionEntity updateOptionEntity(OptionUpdateDTO optionUpdateDTO, OptionEntity optionEntity) {
        if ( optionUpdateDTO == null ) {
            return null;
        }

        optionEntity.setId( optionUpdateDTO.getId() );
        optionEntity.setOptionText( optionUpdateDTO.getOptionText() );

        return optionEntity;
    }

    @Override
    public OptionEntity optionUpdateInDTOToOptionEntity(OptionUpdateDTO optionUpdateDTO) {
        if ( optionUpdateDTO == null ) {
            return null;
        }

        OptionEntity optionEntity = new OptionEntity();

        optionEntity.setId( optionUpdateDTO.getId() );
        optionEntity.setOptionText( optionUpdateDTO.getOptionText() );

        return optionEntity;
    }
}
