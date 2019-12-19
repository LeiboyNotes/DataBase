package com.zl.db.subdb;

import android.database.sqlite.SQLiteDatabase;

import com.zl.db.db.BaseDao;
import com.zl.db.db.BaseDaoFactory;

public class BaseDaoSubFactory extends BaseDaoFactory {
    private static final BaseDaoSubFactory instance = new BaseDaoSubFactory();

    public static BaseDaoSubFactory getInstance() {
        return instance;
    }

    protected SQLiteDatabase sqLiteDatabase;

    //生产basedao对象
    public <T extends BaseDao<M>, M> T getBaseDao(Class<T> daoClass, Class<M> entityClass) {
        BaseDao baseDao = null;
        if (map.get(PrivateDatabaseEnums.database.getValue()) != null) {
            return (T) map.get(PrivateDatabaseEnums.database.getValue());
        }
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(PrivateDatabaseEnums.database.getValue(), null);
        try {
            baseDao = daoClass.newInstance();
            baseDao.init(sqLiteDatabase, entityClass);
            map.put(PrivateDatabaseEnums.database.getValue(), baseDao);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return (T) baseDao;
    }
}
