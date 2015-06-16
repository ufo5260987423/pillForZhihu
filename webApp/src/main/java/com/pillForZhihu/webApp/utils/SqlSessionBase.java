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
    private Transaction transactionx;
    private DatabaseControler databaseControler;

    public SqlSessionBase() {
    }

    public SqlSessionBase(Session session) {
        this.setSession(session);
    }

    public SqlSessionBase(DatabaseControler databaseControler){
        this.setDatabaseControler(databaseControler);
        this.setSession(this.getDatabaseControler().getSession());
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
        this.getTransactionx().commit();
    }

    public Connection close() {
        return this.getSession().close();
    }

    public Object get(Class clazz,Serializable id) {
        return this.getSession().get(clazz,id);
    }

    public Transaction getTransactionx() {
        return transactionx;
    }

    public void setTransactionx(Transaction transactionx) {
        this.transactionx = transactionx;
    }

    public DatabaseControler getDatabaseControler() {
        return databaseControler;
    }

    public void setDatabaseControler(DatabaseControler databaseControler) {
        this.databaseControler = databaseControler;
    }

    public void finalize(){
        this.close();
    }
}
