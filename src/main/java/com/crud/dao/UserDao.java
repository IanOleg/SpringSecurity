package com.crud.dao;

import com.crud.model.Role;
import com.crud.model.User;
import java.util.List;
import java.util.Set;

public interface UserDao {

    void createUsersTable();

    void dropUsersTable();

    void saveUser(User user);

    void mergeUser(User user);

    void removeUser(long id);

    User getUser(long id);

    List<User> getAllUsers();

    void cleanUsersTable();

    User getUserByName(String s);

    Set<Role> getRoles(long id);

    String getPassword(long id);
}
