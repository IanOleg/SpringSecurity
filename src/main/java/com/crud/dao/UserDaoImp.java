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
        for (Object role: user.getRoles()) {
            entityManager.merge(role);
        }
        entityManager.persist(user);
    }

    @Override
    public void mergeUser(User user) {

        entityManager.merge(user);
    }

    @Override
    public void removeUser(long id) {

        entityManager.remove(entityManager.getReference(User.class, id));
    }

    @Override
    public User getUser(long id) {

        return entityManager.find(User.class, (long)id);
    }

    public String getPassword(long id){

        return entityManager.createQuery("select s.password from User s where s.id= :id", String.class)
                            .setParameter("id", id)
                            .getSingleResult();
    }

    public Set<Role> getRoles(long id) {

        List resultList = entityManager.createNativeQuery("select r.id, r.role " +
                                                             "from roles r " +
                                                             "inner join user_role ur " +
                                                             "on r.id = ur.role_id " +
                                                                "and ur.user_id= :id", Role.class)
                                        .setParameter("id", id)
                                        .getResultList();

        return new HashSet<Role>(resultList);
    }

    @Override
    public User getUserByName(String s) {

//        TypedQuery<User> query = entityManager.createQuery("select s from User s where s.name= :name", User.class);
//        query.setParameter("name", s);
//        return query.getSingleResult();

        return entityManager.createQuery("select s from User s where s.name= :name", User.class)
                            .setParameter("name", s)
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