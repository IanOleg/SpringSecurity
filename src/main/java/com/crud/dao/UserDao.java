package com.crud.dao;

import com.crud.model.User;
import java.util.List;

public interface UserDao {

    void createUsersTable();

    void dropUsersTable();

    void saveUser(User user);

    void mergeUser(User user);

    void removeUser(long id);

    User getUser(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
