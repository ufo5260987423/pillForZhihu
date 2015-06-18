package com.pillForZhihu.webApp.dao.utils;

import com.pillForZhihu.webApp.tools.StringProcessor;

/**
 * Created by ufo on 6/15/15.
 */
public class KeyBase implements KeyInf{
    private Integer keyId;
    private String key;

    public String toString(){
        return this.getKey();
    }

    public String getKeyIdName() {
        return StringProcessor.lowerFirstChar(this.getKeyIdName()+"_id");
    }

    public String getKeyName() {
        return this.getClass().getSimpleName();
    }

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
