package com.pillForZhihu.webApp.logistic.keys;

/**
 * Created by ufo on 6/16/15.
 */
public enum UserKEY {
    NAME("name"),GENDER("gender");

    private String key;

    UserKEY(String key){
        this.setKey(key);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
