package com.crud.service;

import com.crud.model.Role;
import com.crud.model.User;
import java.util.List;
import java.util.Set;

public interface UserService {

    void cleanUsersTable();

    void createUsersTable();

    void dropUsersTable();

    void saveUser(User user);

    void mergeUser(User user);

    void mergeUser(User user, boolean addRoles);

    void removeUser(String loginName);

    User getUser(String loginName);

    User getUser(String loginName, boolean addRoles);

    List<User> getAllUsers();

    List<User> getAllUsers(String s);

    public Set<Role> getRoles(String loginName);

    void anyNativeQuery(String text);

    User getReferenceUser(String loginName);
}
