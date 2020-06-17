package com.testkit.build.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionEntity is a Querydsl query type for QuestionEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQuestionEntity extends EntityPathBase<QuestionEntity> {

    private static final long serialVersionUID = 1773494685L;

    public static final QQuestionEntity questionEntity = new QQuestionEntity("questionEntity");

    public final StringPath answer = createString("answer");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<OptionEntity, QOptionEntity> optionEntityList = this.<OptionEntity, QOptionEntity>createList("optionEntityList", OptionEntity.class, QOptionEntity.class, PathInits.DIRECT2);

    public final StringPath questionText = createString("questionText");

    public final NumberPath<Integer> time = createNumber("time", Integer.class);

    public final StringPath type = createString("type");

    public QQuestionEntity(String variable) {
        super(QuestionEntity.class, forVariable(variable));
    }

    public QQuestionEntity(Path<? extends QuestionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuestionEntity(PathMetadata metadata) {
        super(QuestionEntity.class, metadata);
    }

}

