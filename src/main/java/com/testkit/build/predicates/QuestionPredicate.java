package com.testkit.build.predicates;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.testkit.build.dao.OptionRepository;
import com.testkit.build.entity.OptionEntity;
import com.testkit.build.entity.QOptionEntity;
import com.testkit.build.entity.QQuestionEntity;

public class QuestionPredicate {

	@Autowired
	static OptionRepository optionRepository;

	public static BooleanExpression questionTextEq(String questionText) {
		QQuestionEntity qQuestionEntity = QQuestionEntity.questionEntity;

		return qQuestionEntity.questionText.eq(questionText);
	}

	public static BooleanExpression questionTypeEq(String questionType) {
		QQuestionEntity qQuestionEntity = QQuestionEntity.questionEntity;
		return qQuestionEntity.type.eq(questionType);
	}

	public static BooleanExpression questionByOptionExp(String optionText) {
		QQuestionEntity qQuestionEntity = QQuestionEntity.questionEntity;
		QOptionEntity qOptionEntity = QOptionEntity.optionEntity;
		JPQLQuery<OptionEntity> optionEntityContainList = optionEntityContainList = JPAExpressions.select(qOptionEntity)
				.from(qOptionEntity).where(qOptionEntity.optionText.eq(optionText));
		return qQuestionEntity.optionEntityList.any().in(optionEntityContainList);
	}

}
