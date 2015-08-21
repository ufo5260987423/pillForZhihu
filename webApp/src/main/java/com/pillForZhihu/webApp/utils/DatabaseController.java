package com.pillForZhihu.webApp.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ufo on 6/16/15.
 */
@Component
public class DatabaseController {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SqlSessionInf getSqlSession() {
        return new SqlSessionBase(this);
    }

    public Session getSession() {
        return this.getSessionFactory().openSession();
    }
}

