package com.pillForZhihu.webApp.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * Created by ufo on 6/16/15.
 */
public class DatabaseController {
    @Resource(name = "sessionFactory")
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
        System.out.println(sessionFactory);
        return this.getSessionFactory().openSession();
    }
}

