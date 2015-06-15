package com.pillForZhihu.webApp.dao.utils;

/**
 * Created by ufo on 6/15/15.
 */
public class KeyBase implements KeyInf{
    private Integer keyId;
    private String key;

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
