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

        user.setPassword(userDao.getPassword(user.id));
        userDao.mergeUser(user);
    }

    @Override
    public void removeUser(long id) {

        userDao.removeUser(id);
    }

    @Override
    public User getUser(long id) {

        User user = userDao.getUser(id);
        return user;
    }

    @Override
    public User getUser(long id, boolean addRoles) {

        User user = userDao.getUser(id);
        if (addRoles && user != null) {
            user.setRoles(getRoles(user.id));
        }
        return user;
    }

    public Set<Role> getRoles(long id) {

        return userDao.getRoles(id);
    }

    @Override
    public List<User> getAllUsers() {

        List<User> userList = userDao.getAllUsers();
        return userList;
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
