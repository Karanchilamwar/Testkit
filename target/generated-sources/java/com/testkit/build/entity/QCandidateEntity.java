package com.testkit.build.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCandidateEntity is a Querydsl query type for CandidateEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCandidateEntity extends EntityPathBase<CandidateEntity> {

    private static final long serialVersionUID = -1415781486L;

    public static final QCandidateEntity candidateEntity = new QCandidateEntity("candidateEntity");

    public final QUserEntity _super = new QUserEntity(this);

    //inherited
    public final NumberPath<Integer> id = _super.id;

    public final DateTimePath<java.util.Date> registrationDate = createDateTime("registrationDate", java.util.Date.class);

    //inherited
    public final StringPath userEmail = _super.userEmail;

    //inherited
    public final StringPath userMobile = _super.userMobile;

    //inherited
    public final StringPath userName = _super.userName;

    //inherited
    public final StringPath userPassword = _super.userPassword;

    //inherited
    public final EnumPath<com.testkit.build.common.enums.UserType> userType = _super.userType;

    public QCandidateEntity(String variable) {
        super(CandidateEntity.class, forVariable(variable));
    }

    public QCandidateEntity(Path<? extends CandidateEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCandidateEntity(PathMetadata metadata) {
        super(CandidateEntity.class, metadata);
    }

}

