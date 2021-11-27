package com.crud.service;

import com.crud.model.Role;

public interface RoleService {
    void saveRole(Role role);

    void mergeRole(Role role);

    void removeRole(long id);

    Role getRole(long id);
}
