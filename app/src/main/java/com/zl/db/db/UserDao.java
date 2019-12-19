package com.zl.db.db;

import android.util.Log;

import com.zl.db.User;

import java.util.List;

//维护用户的共有数据
public class UserDao extends BaseDao<User> {


    @Override
    public long insert(User entity) {
        //查询该表中的所有用户
        List<User> list = query(new User());
        User where = null;
        for (User user : list) {
            where = new User();
            where.setId(user.getId());
            user.setStatus(0);
            update(user,where);
            Log.e("userDao >>>","用户"+user.getName()+"更改为未登录状态");
        }
        entity.setStatus(1);
        Log.e("userDao >>>","用户"+entity.getName()+"登录");

        return super.insert(entity);
    }

    //获取当前登录的user
    public User getCurrentUser(){
        User user = new User();
        user.setStatus(1);
        List<User> list = query(user);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
