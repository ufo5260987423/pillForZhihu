package com.pillForZhihu.webApp.utils.map;

import com.pillForZhihu.webApp.dao.utils.EntityInf;
import com.pillForZhihu.webApp.dao.utils.KeyInf;
import com.pillForZhihu.webApp.dao.utils.ValueInf;
import com.pillForZhihu.webApp.utils.SqlSessionInf;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ufo on 6/16/15.
 */
public class DatabaseMapBase
        <ENTITY extends EntityInf, KEY extends KeyInf, VALUE extends ValueInf>
        extends HashMap<String, String> implements DatabaseMapInf {

    private Class<KEY> keyClass;
    private Class<VALUE> valueClass;

    private SqlSessionInf sqlSession;
    private ENTITY entity;

    public DatabaseMapBase(ENTITY entity) {
        this.setEntity(entity);
        Type thisType = this.getClass();
        Type[] params = ((ParameterizedType) thisType).getActualTypeArguments();
        this.keyClass = (Class<KEY>) params[1];
        this.valueClass = (Class<VALUE>) params[2];
    }

    public DatabaseMapBase(ENTITY entity, SqlSessionInf sqlSession) {
        this(entity);
        this.setSqlSession(sqlSession);

        this.load();
    }

    public void load() {
        List<VALUE> valuesList = this.sqlSession.createQuery("from "
                + this.valueClass.getSimpleName()
                + " where "
                + this.getEntity().getEntityIdName()
                + "=?")
                .setLong(0, this.getEntity().getEntityId())
                .list();
        for (VALUE value : valuesList)
            this.put(
                    ((KEY) this.getSqlSession().get(this.keyClass, value.getKeyId())).toString()
                    , value.toString());
    }

    private KEY getKey(String key) throws IllegalAccessException, InstantiationException {
        KEY keyInstance = this.keyClass.newInstance();
        List<KEY> keysList = this.getSqlSession().createQuery("from "
                + keyInstance.getKeyName()
                + " where "
                + keyInstance.getKeyIdName()
                + "=?").setString(0, key).list();

        KEY keyDao = keysList.get(0);
        if (null == keyDao) {
            keyDao.setKey(key);
            this.getSqlSession().save(keyDao);
        }
        return keyDao;
    }

    private VALUE getValue(KEY keyDao,String value) throws IllegalAccessException, InstantiationException {
        VALUE valueInstance=this.valueClass.newInstance();
        List<VALUE> valuesList=this.getSqlSession().createQuery("from "
                +valueInstance.getValueName()
                +" where "
                +valueInstance.getKeyIdName()
                +"=? and "
                +valueInstance.getEntityIdName()
                +"=? ")
                .setInteger(0,keyDao.getKeyId())
                .setLong(1,this.getEntity().getEntityId())
                .list();
        VALUE valueDao=valuesList.get(0);

        if(null==valueDao)
            valueDao=this.valueClass.newInstance();

        valueDao.setEntityId(this.getEntity().getEntityId());
        valueDao.setKeyId(keyDao.getKeyId());
        valueDao.setValue(value);

        return valueDao;
    }

    public void save() {
        DatabaseMapBase<ENTITY, KEY, VALUE> oldData =
                new DatabaseMapBase(this.getEntity(), this.getSqlSession().getDatabaseController().getSqlSession());
        try {
            for (String key : this.keySet()) {
                if (!this.get(key).equals(oldData.get(key))) {
                    KEY keyDao = this.getKey(key);
                    VALUE valueDao = this.getValue(keyDao,this.get(key));
                    this.getSqlSession().save(valueDao);
                }
                oldData.remove(key);
            }

            for (String key : oldData.keySet()) {
                KEY keyDao = this.getKey(key);
                VALUE valueDao=this.getValue(keyDao,oldData.get(key));
                this.getSqlSession().delete(valueDao);
            }

            this.getSqlSession().commit();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public SqlSessionInf getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSessionInf sqlSession) {
        this.sqlSession = sqlSession;
    }

    public ENTITY getEntity() {
        return entity;
    }

    public void setEntity(ENTITY entity) {
        this.entity = entity;
    }
}
