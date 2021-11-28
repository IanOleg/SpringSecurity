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

    void removeUser(String loginName);

    User getUser(String loginName);

    List<User> getAllUsers();

    void cleanUsersTable();

    User getUserByName(String loginName);

    Set<Role> getRoles(String loginName);

    String getPassword(String loginName);

    void anyNativeQuery(String text);

    User getReferenceUser(String loginName);
}
