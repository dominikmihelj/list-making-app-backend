package com.dmihelj.list_app.service;

import com.dmihelj.list_app.dao.UserDao;
import com.dmihelj.list_app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public String createUser(User user) {
        int result = userDao.createUser(user);
        if (result > 0) {
            return "User created successfully!";
        } else {
            return "User creation failed!";
        }
    }

    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    public User findUserById(int id) {
        return userDao.findUserById(id);
    }
}
