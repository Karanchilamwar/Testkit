package com.testkit.build.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOptionEntity is a Querydsl query type for OptionEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOptionEntity extends EntityPathBase<OptionEntity> {

    private static final long serialVersionUID = 1541981164L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOptionEntity optionEntity = new QOptionEntity("optionEntity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath optionText = createString("optionText");

    public final QQuestionEntity questionEntity;

    public final NumberPath<Integer> questionId = createNumber("questionId", Integer.class);

    public QOptionEntity(String variable) {
        this(OptionEntity.class, forVariable(variable), INITS);
    }

    public QOptionEntity(Path<? extends OptionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOptionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOptionEntity(PathMetadata metadata, PathInits inits) {
        this(OptionEntity.class, metadata, inits);
    }

    public QOptionEntity(Class<? extends OptionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.questionEntity = inits.isInitialized("questionEntity") ? new QQuestionEntity(forProperty("questionEntity")) : null;
    }

}

