package com.pillForZhihu.webApp.logistic.controllers;

import com.pillForZhihu.webApp.dao.utils.EntityInf;
import com.pillForZhihu.webApp.dao.utils.KeyInf;
import com.pillForZhihu.webApp.dao.utils.ValueInf;
import com.pillForZhihu.webApp.utils.DatabaseController;
import com.pillForZhihu.webApp.utils.SqlSessionInf;
import com.pillForZhihu.webApp.utils.map.DatabaseMapBase;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by ufo on 6/18/15.
 */
public class BaseController<ENTITY extends EntityInf,ENTITY_KEY extends KeyInf,ENTITY_VALUE extends ValueInf> {
    public static final String[] SUCCESS=new String[]{"flag","true"};
    public static final String[] FAIL=new String[]{"flag","false"};

    @Resource(name = "databaseController")
    private DatabaseController databaseController;
    private SqlSessionInf sqlSession;
    private Class<ENTITY> entityClass;
    private Class<ENTITY_KEY>keyClass;
    private Class<ENTITY_VALUE>valueClass;

    private DatabaseMapBase<ENTITY,ENTITY_KEY,ENTITY_VALUE> load(Long entityId){
        try {
            ENTITY entity=this.entityClass.newInstance();
            List<ENTITY> entitysList=sqlSession.createQuery("from "
                    +entity.getEntityIdName()
                    +" where "
                    +entity.getEntityIdName()
                    +"=?").setLong(0,entityId).list();
            if(entitysList.isEmpty())
                return null;

            DatabaseMapBase<ENTITY,ENTITY_KEY,ENTITY_VALUE> result=
                    new DatabaseMapBase<ENTITY, ENTITY_KEY, ENTITY_VALUE>
                            (entity,this.getKeyClass(),this.getValueClass(),sqlSession);
            return result;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected ENTITY saveEntity(ENTITY entity){
        this.sqlSession.save(entity);
        this.sqlSession.commit();
        return entity;
    }

    protected ENTITY getEntity(Long entityId){
        return (ENTITY) this.getSqlSession().get(this.entityClass, entityId);
    }

    protected String[] deleteEntity(ENTITY entity){
        this.sqlSession.delete(entity);
        this.sqlSession.commit();
        return SUCCESS;
    }

    protected Map<String,String> listEntityInfo(Long entityId){
        return this.load(entityId);
    }

    protected String[] getEntityInfo(Long entityId,String entityKey){
        return new String[]{entityKey, this.load(entityId).get(entityKey)};
    }

    protected String[] putEntityInfo(Long entityId,String entityKey,String entityValue){
        DatabaseMapBase<ENTITY,ENTITY_KEY,ENTITY_VALUE> tmp=this.load(entityId);
        if(null==tmp)
            return FAIL;
        tmp.put(entityKey,entityValue);
        tmp.save();
        return SUCCESS;
    }

    protected String[] deleteEntityInfo(Long entityId, String entityKey){
        DatabaseMapBase<ENTITY,ENTITY_KEY,ENTITY_VALUE> tmp=this.load(entityId);
        if(null==tmp)
            return FAIL;
        tmp.remove(entityKey);
        tmp.save();
        return SUCCESS;
    }

    public void setSqlSession(SqlSessionInf sqlSession){
        this.sqlSession=sqlSession;
    }

    public SqlSessionInf getSqlSession(){
        if(null==this.sqlSession)
            this.setSqlSession(this.getDatabaseController().getSqlSession());

        return this.sqlSession;
    }

    public DatabaseController getDatabaseController() {
        return databaseController;
    }

    public void setDatabaseController(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    public void setEntityClass(Class<ENTITY> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<ENTITY> getEntityClass(){
        return this.entityClass;
    }

    public Class<ENTITY_KEY> getKeyClass() {
        return keyClass;
    }

    public void setKeyClass(Class<ENTITY_KEY> keyClass) {
        this.keyClass = keyClass;
    }

    public Class<ENTITY_VALUE> getValueClass() {
        return valueClass;
    }

    public void setValueClass(Class<ENTITY_VALUE> valueClass) {
        this.valueClass = valueClass;
    }
}
