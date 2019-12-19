package com.zl.db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zl.db.db.BaseDao;
import com.zl.db.db.BaseDaoFactory;
import com.zl.db.db.UserDao;
import com.zl.db.subdb.BaseDaoSubFactory;
import com.zl.db.subdb.PhotoDao;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int i = 0;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDao = BaseDaoFactory.getInstance().getBaseDao(UserDao.class,User.class);
    }

    public void insert(View view) {
        BaseDao baseDao = BaseDaoFactory.getInstance().getBaseDao(UserDao.class,User.class);
        baseDao.insert(new User(1,"zl1","321"));
        baseDao.insert(new User(2,"zl2","321"));
        baseDao.insert(new User(3,"zl3","321"));
        baseDao.insert(new User(4,"zl4","321"));
        baseDao.insert(new User(5,"zl5","321"));
        baseDao.insert(new User(6,"zl6","321"));
    }

    public void select(View view) {
        BaseDao baseDao = BaseDaoFactory.getInstance().getBaseDao(UserDao.class,User.class);
        User where = new User();
        where.setPassword("321");
        List<User> list  = baseDao.query(where);
        Log.e("MainActivity >>> query:","list size is "+list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

    public void update(View view) {

//        BaseDao baseDao = BaseDaoFactory.getInstance().getBaseDao(User.class);
//        User user = new User();
//        user.setId(2);
//        user.setName("zlupadate");
//        user.setPassword("321");
//        User where = new User();
//        where.setId(2);
//        baseDao.update(user,where);
//        Toast.makeText(MainActivity.this,"执行成功",Toast.LENGTH_LONG).show();
    }

    public void delete(View view) {

//        BaseDao baseDao = BaseDaoFactory.getInstance().getBaseDao(User.class);
//        User where = new User();
//        where.setName("zlupadate");
//        baseDao.delete(where);
    }

    public void login(View view) {
        //服务器返回的信息
        User user = new User();
        user.setName("zl"+(i++));
        user.setPassword("123456");
        user.setId(i);
        userDao.insert(user);
        Toast.makeText(MainActivity.this,"执行成功",Toast.LENGTH_LONG).show();
    }

    public void library_insert(View view) {

        Photo photo = new Photo();
        photo.setPath("data/data/xxx.jpg");
        photo.setTime(new Date().toString());
        PhotoDao photoDao = BaseDaoSubFactory.getInstance().getBaseDao(PhotoDao.class,Photo.class);
        photoDao.insert(photo);
    }
}
