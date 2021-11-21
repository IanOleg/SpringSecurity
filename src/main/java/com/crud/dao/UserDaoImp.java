package com.crud.dao;

import com.crud.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceUnit
    public EntityManagerFactory managerFactory;
    public EntityTransaction entityTransaction = null;
    public EntityManager entityManager = null;

    @Override
    public void createUsersTable() {
        try{
            entityManager =  managerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Query query = entityManager.createNativeQuery("CREATE TABLE IF NOT EXISTS users(id MEDIUMINT NOT NULL AUTO_INCREMENT primary key, name CHAR(30) NOT NULL, lastName CHAR(30) NOT NULL, age TINYINT NOT NULL)");
            query.executeUpdate();
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public void dropUsersTable() {
        try{
            entityManager = managerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Query query = entityManager.createNativeQuery("drop table if exists users");
            query.executeUpdate();
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public void saveUser(User user) {
        try{
            entityManager = managerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(user);
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public void mergeUser(User user) {
        try{
            entityManager = managerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.merge(user);
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public void removeUser(long id) {
        try{
            entityManager = managerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.remove(entityManager.find(User.class, id));
//            Query query = entityManager.createQuery("delete from User where id = :pId");
//            query.setParameter("pId", id);
//            query.executeUpdate();
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public User getUser(long id) {
        User user = null;
        try{
            entityManager = managerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            user = entityManager.find(User.class, (long)id);
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = null;
        try{
            entityManager = managerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            Query query =  entityManager.createQuery("select s from User s");
            allUsers = query.getResultList();
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        try{
            entityManager = managerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Query query = entityManager.createNativeQuery("truncate users");
            query.executeUpdate();
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }
}