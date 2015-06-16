package com.pillForZhihu.webApp.utils;

import org.hibernate.Query;
import org.hibernate.Session;

import java.io.Serializable;
import java.sql.Connection;

/**
 * Created by ufo on 6/16/15.
 */
public interface SqlSessionInf {
    Query createQuery(String sql);

    Session getSession();

    void setSession(Session session);

    Serializable save(Object obj);

    void delete(Object obj);

    void commit();

    Connection close();

    Object get(Class clazz,Serializable id);

    DatabaseControler getDatabaseControler();

    void setDatabaseControler(DatabaseControler databaseControler);
}
