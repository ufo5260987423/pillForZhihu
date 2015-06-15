package com.pillForZhihu.webApp.dao;

import com.pillForZhihu.webApp.dao.utils.EntityBase;

/**
 * Created by ufo on 6/15/15.
 */
public class User extends EntityBase{
    private String user_pwd;
    private String user_name;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }
}
