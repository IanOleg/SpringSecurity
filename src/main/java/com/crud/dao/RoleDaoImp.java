package com.crud.dao;

import com.crud.model.Role;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImp implements RoleDao{

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public void saveRole(Role role) {

        entityManager.persist(role);
    }

    @Override
    public void mergeRole(Role role) {

        entityManager.merge(role);
    }

    @Override
    public void removeRole(long id) {

        entityManager.remove(getReferenceRole(id));
    }

    @Override
    public Role getRole(long id) {

        return entityManager.find(Role.class, (long)id);
    }

    @Override
    public Role getReferenceRole(long id) {

        return entityManager.getReference(Role.class, (long)id);
    }
}
