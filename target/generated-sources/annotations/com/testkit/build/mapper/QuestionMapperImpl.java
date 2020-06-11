package com.testkit.build.mapper;

import com.testkit.build.dto.OptionDTO;
import com.testkit.build.dto.OptionInDTO;
import com.testkit.build.dto.OptionUpdateDTO;
import com.testkit.build.dto.QuestionDTO;
import com.testkit.build.dto.QuestionInDTO;
import com.testkit.build.dto.QuestionUpdateDTO;
import com.testkit.build.entity.OptionEntity;
import com.testkit.build.entity.QuestionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-11T12:05:06+0530",
    comments = "version: 1.4.0.Beta1, compiler: Eclipse JDT (IDE) 3.21.0.v20200304-1404, environment: Java 1.8.0_251 (Oracle Corporation)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public QuestionEntity questionInDTOToQuestionEntity(QuestionInDTO questionInDTO) {
        if ( questionInDTO == null ) {
            return null;
        }

        QuestionEntity questionEntity = new QuestionEntity();

        questionEntity.setOptionEntityList( optionInDTOListToOptionEntityList( questionInDTO.getOptionInDTOList() ) );
        questionEntity.setAnswer( questionInDTO.getAnswer() );
        questionEntity.setQuestionText( questionInDTO.getQuestionText() );
        questionEntity.setTime( questionInDTO.getTime() );
        questionEntity.setType( questionInDTO.getType() );

        return questionEntity;
    }

    @Override
    public QuestionDTO questionEntityToQuestionDTO(QuestionEntity questionEntity) {
        if ( questionEntity == null ) {
            return null;
        }

        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setOptionDTOList( optionEntityListToOptionDTOList( questionEntity.getOptionEntityList() ) );
        questionDTO.setAnswer( questionEntity.getAnswer() );
        questionDTO.setId( questionEntity.getId() );
        questionDTO.setQuestionText( questionEntity.getQuestionText() );
        questionDTO.setTime( questionEntity.getTime() );
        questionDTO.setType( questionEntity.getType() );

        return questionDTO;
    }

    @Override
    public QuestionEntity updateQuestionEntity(QuestionInDTO questionInDTO, QuestionEntity questionEntity) {
        if ( questionInDTO == null ) {
            return null;
        }

        if ( questionEntity.getOptionEntityList() != null ) {
            List<OptionEntity> list = optionInDTOListToOptionEntityList( questionInDTO.getOptionInDTOList() );
            if ( list != null ) {
                questionEntity.getOptionEntityList().clear();
                questionEntity.getOptionEntityList().addAll( list );
            }
            else {
                questionEntity.setOptionEntityList( null );
            }
        }
        else {
            List<OptionEntity> list = optionInDTOListToOptionEntityList( questionInDTO.getOptionInDTOList() );
            if ( list != null ) {
                questionEntity.setOptionEntityList( list );
            }
        }
        questionEntity.setAnswer( questionInDTO.getAnswer() );
        questionEntity.setQuestionText( questionInDTO.getQuestionText() );
        questionEntity.setTime( questionInDTO.getTime() );
        questionEntity.setType( questionInDTO.getType() );

        return questionEntity;
    }

    @Override
    public QuestionEntity updateQuestionEntity(QuestionUpdateDTO questionUpdateDTO, QuestionEntity questionEntity) {
        if ( questionUpdateDTO == null ) {
            return null;
        }

        if ( questionEntity.getOptionEntityList() != null ) {
            List<OptionEntity> list = optionUpdateDTOListToOptionEntityList( questionUpdateDTO.getOptionUpdateDTOs() );
            if ( list != null ) {
                questionEntity.getOptionEntityList().clear();
                questionEntity.getOptionEntityList().addAll( list );
            }
            else {
                questionEntity.setOptionEntityList( null );
            }
        }
        else {
            List<OptionEntity> list = optionUpdateDTOListToOptionEntityList( questionUpdateDTO.getOptionUpdateDTOs() );
            if ( list != null ) {
                questionEntity.setOptionEntityList( list );
            }
        }
        questionEntity.setAnswer( questionUpdateDTO.getAnswer() );
        questionEntity.setId( questionUpdateDTO.getId() );
        questionEntity.setQuestionText( questionUpdateDTO.getQuestionText() );
        questionEntity.setTime( questionUpdateDTO.getTime() );
        questionEntity.setType( questionUpdateDTO.getType() );

        return questionEntity;
    }

    protected OptionEntity optionInDTOToOptionEntity(OptionInDTO optionInDTO) {
        if ( optionInDTO == null ) {
            return null;
        }

        OptionEntity optionEntity = new OptionEntity();

        optionEntity.setOptionText( optionInDTO.getOptionText() );

        return optionEntity;
    }

    protected List<OptionEntity> optionInDTOListToOptionEntityList(List<OptionInDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<OptionEntity> list1 = new ArrayList<OptionEntity>( list.size() );
        for ( OptionInDTO optionInDTO : list ) {
            list1.add( optionInDTOToOptionEntity( optionInDTO ) );
        }

        return list1;
    }

    protected OptionDTO optionEntityToOptionDTO(OptionEntity optionEntity) {
        if ( optionEntity == null ) {
            return null;
        }

        OptionDTO optionDTO = new OptionDTO();

        optionDTO.setId( optionEntity.getId() );
        optionDTO.setOptionText( optionEntity.getOptionText() );

        return optionDTO;
    }

    protected List<OptionDTO> optionEntityListToOptionDTOList(List<OptionEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<OptionDTO> list1 = new ArrayList<OptionDTO>( list.size() );
        for ( OptionEntity optionEntity : list ) {
            list1.add( optionEntityToOptionDTO( optionEntity ) );
        }

        return list1;
    }

    protected OptionEntity optionUpdateDTOToOptionEntity(OptionUpdateDTO optionUpdateDTO) {
        if ( optionUpdateDTO == null ) {
            return null;
        }

        OptionEntity optionEntity = new OptionEntity();

        optionEntity.setId( optionUpdateDTO.getId() );
        optionEntity.setOptionText( optionUpdateDTO.getOptionText() );

        return optionEntity;
    }

    protected List<OptionEntity> optionUpdateDTOListToOptionEntityList(List<OptionUpdateDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<OptionEntity> list1 = new ArrayList<OptionEntity>( list.size() );
        for ( OptionUpdateDTO optionUpdateDTO : list ) {
            list1.add( optionUpdateDTOToOptionEntity( optionUpdateDTO ) );
        }

        return list1;
    }
}
