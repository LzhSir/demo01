package com.heliang.dao;

import com.heliang.entity.User;
import com.heliang.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

    public long create(User user) {
        String sql = "insert into user (username, password, nickname) values (?, ?, ?)";
        Object[] params = new Object[] {user.getUsername(), user.getPassword(), user.getNickname()};
        long result = -1;
        try {
            result = runner.insert(sql, new ScalarHandler<>(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<User> query() {
        String sql = "select * from user";
        List<User> users = new ArrayList<>();
        try {
            users = runner.query(sql, new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User queryByUsername(String username) {
        String sql = "select * from user where username = ?";
        Object[] params = new Object[] {username};
        User user = null;
        try {
            user = runner.query(sql, new BeanHandler<>(User.class), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
