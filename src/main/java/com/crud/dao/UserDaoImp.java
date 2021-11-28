package com.crud.dao;

import com.crud.model.Role;
import com.crud.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public void saveUser(User user) {

        RoleDao roleDao = new RoleDaoImp();
        if (user.getRoles() != null){
            for (Object role: user.getRoles()) {
                entityManager.merge(role);
            }
        }
        entityManager.persist(user);
    }

    @Override
    public void mergeUser(User user) {

        entityManager.merge(user);
    }

    @Override
    public void removeUser(String loginName) {

        entityManager.remove(entityManager.getReference(User.class, loginName));
    }

    @Override
    public User getReferenceUser(String loginName) {

        return entityManager.getReference(User.class, loginName);
    }

    @Override
    public User getUser(String loginName) {

        return entityManager.find(User.class, loginName);
    }

    public String getPassword(String loginName){

        return entityManager.createQuery("select s.password from User s where s.loginName= :loginName", String.class)
                            .setParameter("loginName", loginName)
                            .getSingleResult();
    }

    public Set<Role> getRoles(String loginName) {

        List resultList = entityManager.createNativeQuery("select r.role " +
                                                             "from roles r " +
                                                             "inner join user_role ur " +
                                                             "on r.role = ur.role " +
                                                                "and ur.loginName= :loginName", Role.class)
                                        .setParameter("loginName", loginName)
                                        .getResultList();

        return new HashSet<Role>(resultList);
    }

    @Override
    public User getUserByName(String s) {

        return entityManager.createQuery("select s from User s where s.loginName= :loginName", User.class)
                            .setParameter("loginName", s)
                            .getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {

        return entityManager.createQuery("select s from User s").getResultList();
    }

    @Override
    public void cleanUsersTable() {

        entityManager.createNativeQuery("truncate users")
                     .executeUpdate();
    }

    @Override
    public void createUsersTable() {

        entityManager.createNativeQuery("CREATE TABLE IF NOT EXISTS users(id MEDIUMINT NOT NULL AUTO_INCREMENT primary key, name CHAR(30) NOT NULL, lastName CHAR(30) NOT NULL, age TINYINT NOT NULL)")
                     .executeUpdate();
    }

    @Override
    public void dropUsersTable() {

        Query query = entityManager.createNativeQuery("drop table if exists users");
        query.executeUpdate();
    }

    @Override
    public void anyNativeQuery(String text) {

        entityManager.createNativeQuery(text)
                     .executeUpdate();
    }
}