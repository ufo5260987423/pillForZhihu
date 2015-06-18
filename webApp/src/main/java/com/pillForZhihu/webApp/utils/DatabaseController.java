package com.pillForZhihu.webApp.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by ufo on 6/16/15.
 */
public class DatabaseController {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        DatabaseController.sessionFactory = sessionFactory;
    }

    public SqlSessionInf getSqlSession() {
        return new SqlSessionBase(getSessionFactory().openSession());
    }

    public Session getSession(){
        return getSessionFactory().openSession();
    }
}