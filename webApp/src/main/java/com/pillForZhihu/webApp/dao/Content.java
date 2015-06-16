package com.pillForZhihu.webApp.dao;

import com.pillForZhihu.webApp.dao.utils.EntityBase;

import java.sql.Timestamp;

/**
 * Created by ufo on 6/15/15.
 *
 * Content类用来描述一切的长内容。对应到知乎的业务逻辑当中，Content可以表示提问的内容和回答的内容。
 * 当然了，你可以使用Content_key和Content_value来实现一个key-value结构来实现回答和提问的区分。
 */
public class Content extends EntityBase{
    private String content;
    private Timestamp content_timestamp;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getContent_timestamp() {
        return content_timestamp;
    }

    public void setContent_timestamp(Timestamp content_timestamp) {
        this.content_timestamp = content_timestamp;
    }
}
