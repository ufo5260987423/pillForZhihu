package com.pillForZhihu.webApp.dao;

import com.pillForZhihu.webApp.dao.utils.EntityBase;

/**
 * Created by ufo on 6/16/15.
 */
public class User_edge extends EntityBase {
    private Long from_user_id;
    private Long to_user_id;
    private String user_edge;

    public Long getFrom_user_id() {
        return from_user_id;
    }

    public void setFrom_user_id(Long from_user_id) {
        this.from_user_id = from_user_id;
    }

    public Long getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(Long to_user_id) {
        this.to_user_id = to_user_id;
    }

    public String getUser_edge() {
        return user_edge;
    }

    public void setUser_edge(String user_edge) {
        this.user_edge = user_edge;
    }
}
