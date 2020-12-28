package com.heliang.service;

import com.heliang.dao.UserDao;
import com.heliang.entity.User;

import java.util.List;
import java.util.Optional;

public class UserService {

    private static final UserDao userDao = new UserDao();

    public long create(User user) {
        return userDao.create(user);
    }

    public List<User> query() {
        return userDao.query();
    }

    public Optional<User> queryByUsername(String username) {
        return Optional.ofNullable(userDao.queryByUsername(username));
    }
}
