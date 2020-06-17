package com.testkit.build.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QResourceEntity is a Querydsl query type for ResourceEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QResourceEntity extends EntityPathBase<ResourceEntity> {

    private static final long serialVersionUID = -342742075L;

    public static final QResourceEntity resourceEntity = new QResourceEntity("resourceEntity");

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

    public QResourceEntity(String variable) {
        super(ResourceEntity.class, forVariable(variable));
    }

    public QResourceEntity(Path<? extends ResourceEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResourceEntity(PathMetadata metadata) {
        super(ResourceEntity.class, metadata);
    }

}

