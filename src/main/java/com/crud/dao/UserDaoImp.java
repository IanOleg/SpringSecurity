package com.crud.dao;

import com.crud.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public void createUsersTable() {
        Query query = entityManager.createNativeQuery("CREATE TABLE IF NOT EXISTS users(id MEDIUMINT NOT NULL AUTO_INCREMENT primary key, name CHAR(30) NOT NULL, lastName CHAR(30) NOT NULL, age TINYINT NOT NULL)");
        query.executeUpdate();
    }

    @Override
    public void dropUsersTable() {
        Query query = entityManager.createNativeQuery("drop table if exists users");
        query.executeUpdate();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void mergeUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void removeUser(long id) {
        entityManager.remove(entityManager.find(User.class, id));
//            Query query = entityManager.createQuery("delete from Role where id = :pId");
//            query.setParameter("pId", id);
//            query.executeUpdate();

    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, (long)id);
    }

    @Override
    public User getUserByName(String s) {
        TypedQuery<User> query = entityManager.createQuery("select s from User s where s.name= :name", User.class);
        query.setParameter("name", s);
        return query.getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        Query query =  entityManager.createQuery("select s from User s");
        return query.getResultList();
    }

    @Override
    public void cleanUsersTable() {
        Query query = entityManager.createNativeQuery("truncate users");
        query.executeUpdate();
    }
}