package com.searchJob.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecruiment is a Querydsl query type for Recruiment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRecruiment extends EntityPathBase<Recruiment> {

    private static final long serialVersionUID = 1480204729L;

    public static final QRecruiment recruiment = new QRecruiment("recruiment");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath id = createString("id");

    public final StringPath idCustomer = createString("idCustomer");

    public final StringPath idPostRecruiment = createString("idPostRecruiment");

    public final NumberPath<Byte> isDeleted = createNumber("isDeleted", Byte.class);

    public final StringPath namefileCV = createString("namefileCV");

    public final StringPath status = createString("status");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public final StringPath uploadCVId = createString("uploadCVId");

    public QRecruiment(String variable) {
        super(Recruiment.class, forVariable(variable));
    }

    public QRecruiment(Path<? extends Recruiment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecruiment(PathMetadata metadata) {
        super(Recruiment.class, metadata);
    }

}

