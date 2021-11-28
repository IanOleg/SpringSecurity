package com.crud.service;

import com.crud.dao.*;
import com.crud.model.Role;
import com.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Component
@Transactional
public class UserServiceImp implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public void saveUser(User user) {

        userDao.saveUser(user);
    }

    @Override
    public void mergeUser(User user) {

        user.setPassword(userDao.getPassword(user.loginName));
        userDao.mergeUser(user);
    }

    @Override
    public void mergeUser(User user, boolean addRoles) {

        user.setPassword(userDao.getPassword(user.loginName));

        if (!addRoles) {
            user.setRoles(getRoles(user.loginName));
        }
        userDao.mergeUser(user);
    }

    @Override
    public void removeUser(String loginName) {

        userDao.removeUser(loginName);
    }

    @Override
    public User getUser(String loginName) {

        User user = userDao.getUser(loginName);
        return user;
    }

    @Override
    public User getUser(String loginName, boolean addRoles) {

        User user = userDao.getUser(loginName);
        if (addRoles && user != null) {
            user.setRoles(getRoles(user.loginName));
        }
        return user;
    }



    @Override
    public List<User> getAllUsers() {

        List<User> userList = userDao.getAllUsers();
        return userList;
    }

    @Override
    public Set<Role> getRoles(String loginName) {
        return userDao.getRoles(loginName);
    }

    @Override
    public void cleanUsersTable() {

        userDao.cleanUsersTable();
    }

    @Override
    public void createUsersTable() {

        userDao.createUsersTable();
    }

    @Override
    public void dropUsersTable() {

        userDao.dropUsersTable();
    }

    @Override
    public void anyNativeQuery(String text) {

        userDao.anyNativeQuery(text);
    }
}
