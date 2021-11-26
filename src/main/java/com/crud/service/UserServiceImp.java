package com.crud.service;

import com.crud.dao.*;
import com.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void createUsersTable() {
        userDao.createUsersTable();
    }

    @Override
    @Transactional
    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void mergeUser(User user) {
        user.setPassword(this.getUser(user.id).getPassword());
        userDao.mergeUser(user);
    }

    @Override
    @Transactional
    public void removeUser(long id) {
        userDao.removeUser(id);
    }

    @Override
    @Transactional
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<User> userList = userDao.getAllUsers();
        return userList;
    }

    @Override
    @Transactional
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
