package com.guoqiang.ormlite.dao;

import android.content.Context;

import com.guoqiang.ormlite.bean.User;
import com.guoqiang.ormlite.helper.DataBaseHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by wangguoqiang on 2016/11/1.
 */
public class UserDao {

    private Dao<User, Integer> userDao;

    public UserDao(Context context) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance(context);
        try {
            userDao = dataBaseHelper.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一条数据，一个对象
     *
     * @param user
     * @return
     */
    public long addUser(User user) {
        int id = 0;
        try {
            id = userDao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * @param users
     */
    public void addAll(List<User> users) {
        for (User user : users) {
            addUser(user);
        }
    }

    /**
     * 查询表中所有属性
     *
     * @return
     */
    public List<User> findAll() {
        try {
            return userDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public User findOne(String type,int id,String name) {
        try {
            User user = null;
            if (type.equalsIgnoreCase("first")) {
                QueryBuilder<User, Integer> queryBuilder = userDao.queryBuilder();
                List<User> lists = queryBuilder.query();
                user = lists.get(0);
            } else if (type.equalsIgnoreCase("id")) {
                user =  userDao.queryForId(id);
            }

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据对象删除某条数据
     *
     * @param user
     */
    public void delete(User user) {
        DeleteBuilder deleteBuilder = userDao.deleteBuilder();
        try {
            deleteBuilder.where().eq("name", user.getName());
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     */
    public void updataOne(String newName,long id) {
        try {
            User user = new User(newName, 28);
            user.set_id(id);
            userDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除所有
     *
     * @param users
     */
    public void removeAll(List<User> users) {
        for (User user : users) {
            delete(user);
        }
    }



}
