package com.zl.db.subdb;

import com.zl.db.User;
import com.zl.db.db.BaseDaoFactory;
import com.zl.db.db.UserDao;

import java.io.File;

public enum PrivateDatabaseEnums {

    database("");
    private String value;
    PrivateDatabaseEnums(String value){

    }

    public String getValue(){
        UserDao userDao = BaseDaoFactory.getInstance().getBaseDao(UserDao.class, User.class);
        if (userDao != null) {
            User user = userDao.getCurrentUser();
            if (user != null) {
                File file = new File("data/data/com.zl.db/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                return file.getAbsolutePath()+"/u_"+user.getId()+"_private.db";
            }
        }
        return null;
    }
}
