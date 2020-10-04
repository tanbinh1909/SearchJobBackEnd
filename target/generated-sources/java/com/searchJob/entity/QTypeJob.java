package com.searchJob.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTypeJob is a Querydsl query type for TypeJob
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTypeJob extends EntityPathBase<TypeJob> {

    private static final long serialVersionUID = -944581410L;

    public static final QTypeJob typeJob = new QTypeJob("typeJob");

    public final StringPath id = createString("id");

    public final StringPath nameType = createString("nameType");

    public QTypeJob(String variable) {
        super(TypeJob.class, forVariable(variable));
    }

    public QTypeJob(Path<? extends TypeJob> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTypeJob(PathMetadata metadata) {
        super(TypeJob.class, metadata);
    }

}

