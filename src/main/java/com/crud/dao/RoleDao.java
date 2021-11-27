package com.crud.dao;

import com.crud.model.Role;

public interface RoleDao {

    void saveRole(Role role);

    void mergeRole(Role role);

    void removeRole(long id);

    Role getRole(long id);

    Role getReferenceRole(long id);
}
