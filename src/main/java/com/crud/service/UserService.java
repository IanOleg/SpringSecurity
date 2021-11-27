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

    void removeUser(long id);

    User getUser(long id);

    User getUser(long id, boolean addRoles);

    List<User> getAllUsers();

    public Set<Role> getRoles(long id);

    void anyNativeQuery(String text);
}
