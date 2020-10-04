package com.searchJob.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPostRecruiment is a Querydsl query type for PostRecruiment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPostRecruiment extends EntityPathBase<PostRecruiment> {

    private static final long serialVersionUID = -1375071239L;

    public static final QPostRecruiment postRecruiment = new QPostRecruiment("postRecruiment");

    public final StringPath address = createString("address");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath dateWord = createString("dateWord");

    public final StringPath deadline = createString("deadline");

    public final StringPath degree = createString("degree");

    public final StringPath description = createString("description");

    public final StringPath id = createString("id");

    public final StringPath idCustomer = createString("idCustomer");

    public final NumberPath<Byte> idDeleted = createNumber("idDeleted", Byte.class);

    public final StringPath idTypeJob = createString("idTypeJob");

    public final StringPath image = createString("image");

    public final StringPath nameCompony = createString("nameCompony");

    public final StringPath nameTypeJob = createString("nameTypeJob");

    public final NumberPath<Float> salary = createNumber("salary", Float.class);

    public final StringPath specialize = createString("specialize");

    public final StringPath status = createString("status");

    public final StringPath title = createString("title");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QPostRecruiment(String variable) {
        super(PostRecruiment.class, forVariable(variable));
    }

    public QPostRecruiment(Path<? extends PostRecruiment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPostRecruiment(PathMetadata metadata) {
        super(PostRecruiment.class, metadata);
    }

}

