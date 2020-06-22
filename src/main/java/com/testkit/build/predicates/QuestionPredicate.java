package com.testkit.build.predicates;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.testkit.build.entity.QQuestionEntity;

public class QuestionPredicate {

	public static BooleanExpression questionTextEq(String questionText) {
		QQuestionEntity qQuestionEntity = QQuestionEntity.questionEntity;
		return qQuestionEntity.questionText.eq(questionText);
	}

	public static BooleanExpression questionTypeEq(String questionType) {
		QQuestionEntity qQuestionEntity = QQuestionEntity.questionEntity;
		return qQuestionEntity.type.eq(questionType);
	}
}
