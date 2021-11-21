package com.crud.service;

import com.crud.model.User;
import java.util.List;

public interface UserService {

    void cleanUsersTable();

    void createUsersTable();

    void dropUsersTable();

    void saveUser(User user);

    void mergeUser(User user);

    void removeUser(long id);

    User getUser(long id);

    List<User> getAllUsers();
}
