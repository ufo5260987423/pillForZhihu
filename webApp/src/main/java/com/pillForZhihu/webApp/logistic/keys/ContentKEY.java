package com.pillForZhihu.webApp.logistic.keys;

/**
 * Created by ufo on 6/16/15.
 */
public enum ContentKEY {
    AUTHOR_ID("author_id"),QUESTIOM_ID("question_id"),ANSWER_ID("answer_id"),
    QUESTION_TITLE("question_title"),COMMENT_ID("comment_id");

    private String key;

    ContentKEY(String key){
        this.setKey(key);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
