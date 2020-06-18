package com.testkit.build.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdminEntity is a Querydsl query type for AdminEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAdminEntity extends EntityPathBase<AdminEntity> {

    private static final long serialVersionUID = 647810654L;

    public static final QAdminEntity adminEntity = new QAdminEntity("adminEntity");

    public final QUserEntity _super = new QUserEntity(this);

    public final StringPath adminLevel = createString("adminLevel");

    //inherited
    public final NumberPath<Integer> id = _super.id;

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

    public QAdminEntity(String variable) {
        super(AdminEntity.class, forVariable(variable));
    }

    public QAdminEntity(Path<? extends AdminEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdminEntity(PathMetadata metadata) {
        super(AdminEntity.class, metadata);
    }

}

