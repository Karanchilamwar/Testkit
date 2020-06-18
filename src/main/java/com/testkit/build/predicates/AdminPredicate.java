package com.testkit.build.predicates;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.testkit.build.entity.QAdminEntity;

public class AdminPredicate {
	public static BooleanExpression userNameEq(String questionText) {
		QAdminEntity adminEntity = QAdminEntity.adminEntity;

		return adminEntity.userName.eq(questionText);
	}
}
