package com.pillForZhihu.webApp.dao.utils;

/**
 * Created by ufo on 6/15/15.
 */
public class ValueBase implements ValueInf{
    private Long valueId;
    private Long entityId;
    private Integer keyId;
    private String value;

    public Long getValueId() {
        return valueId;
    }

    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
