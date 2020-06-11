package com.testkit.build.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testkit.build.entity.OptionEntity;
import com.testkit.build.entity.QuestionEntity;

@Repository("QuestionRepository")
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {

	QuestionEntity findQuestionEntityByQuestionTextAndType(String questionText, String type);

	OptionEntity save(OptionEntity optionEntity);

}
