package com.testkit.build.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testkit.build.entity.OptionEntity;
import com.testkit.build.entity.QuestionEntity;

@Repository("OptionRepository")
public interface OptionRepository extends JpaRepository<OptionEntity, Integer> {

	Optional<OptionEntity> findByOptionText(String optionText);

	List<Optional<OptionEntity>> findByQuestionEntity(QuestionEntity questionEntity);

	List<Optional<OptionEntity>> findByQuestionId(int question_id);

}
