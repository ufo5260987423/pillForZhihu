package com.pillForZhihu.webApp.utils;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.sql.Connection;

/**
 * Created by ufo on 6/16/15.
 */
public class SqlSessionBase implements SqlSessionInf {
    private Session session;
    private Transaction transaction;
    private DatabaseController databaseController;

    public SqlSessionBase() {
    }

    public SqlSessionBase(Session session) {
        this.setSession(session);
    }

    public SqlSessionBase(DatabaseController databaseController){
        this.setDatabaseController(databaseController);
        this.setSession(this.getDatabaseController().getSession());
    }

    public Query createQuery(String sql){
        return this.getSession().createQuery(sql);
    }

    public Session getSession() {
        return this.session;
    }

    public void setSession(Session session) {
        this.session = session;
        this.getSession().beginTransaction();
    }

    public Serializable save(Object obj) {
        return this.getSession().save(obj);
    }

    public void delete(Object obj) {
        this.getSession().delete(obj);
    }

    public void commit() {
        this.getTransaction().commit();
    }

    public Connection close() {
        return this.getSession().close();
    }

    public Object get(Class clazz,Serializable id) {
        return this.getSession().get(clazz,id);
    }

    public DatabaseController getDatabaseController() {
        return databaseController;
    }

    public void setDatabaseController(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    public void finalize(){
        this.close();
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
