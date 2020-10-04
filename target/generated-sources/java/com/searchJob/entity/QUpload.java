package com.searchJob.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUpload is a Querydsl query type for Upload
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUpload extends EntityPathBase<Upload> {

    private static final long serialVersionUID = -841545754L;

    public static final QUpload upload = new QUpload("upload");

    public final StringPath contentType = createString("contentType");

    public final ArrayPath<byte[], Byte> dataCV = createArray("dataCV", byte[].class);

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final StringPath uploadCVId = createString("uploadCVId");

    public QUpload(String variable) {
        super(Upload.class, forVariable(variable));
    }

    public QUpload(Path<? extends Upload> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUpload(PathMetadata metadata) {
        super(Upload.class, metadata);
    }

}

