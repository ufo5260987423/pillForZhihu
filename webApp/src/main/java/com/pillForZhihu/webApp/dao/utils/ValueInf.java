package com.pillForZhihu.webApp.dao.utils;

/**
 * Created by ufo on 6/15/15.
 */
public interface ValueInf {
    Long getValueId();

    void setValueId(Long valueId);

    Long getEntityId();

    void setEntityId(Long entityId);

    Integer getKeyId();

    void setKeyId(Integer keyId);

    String getValue();

    void setValue(String value);

    String toString();

    String getValueName();

    String getValueIdName();

    String getKeyIdName();

    String getEntityIdName();
}
