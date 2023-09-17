package dev.bernouy.cms.common;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class AbstractDocument{

    @MongoId
    private String id;

    public String getId() {
        return id;
    }

}
