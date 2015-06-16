package com.pillForZhihu.webApp.dao.utils;

import com.pillForZhihu.webApp.tools.StringProcessor;

/**
 * Created by ufo on 6/15/15.
 */
public class EntityBase implements EntityInf {
    private Long entityId;

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }


    public String getEntityName() {
        return this.getClass().getSimpleName();
    }

    public String getEntityIdName() {
        return StringProcessor.lowerFirstChar(this.getEntityName()) + "_id";
    }
}
