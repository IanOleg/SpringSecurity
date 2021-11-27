package com.crud.service;

import com.crud.dao.UserDao;
import com.crud.model.Role;
import com.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class testData {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserDao userDao;

    @PostConstruct
    void testData(){

//        userDao.anyNativeQuery("truncate ianoleg242.users");
//        userDao.anyNativeQuery("truncate ianoleg242.roles");
//        userDao.anyNativeQuery("truncate ianoleg242.user_role");
//
//        userDao.anyNativeQuery("drop table if exists ianoleg242.users");
//        userDao.anyNativeQuery("drop table if exists ianoleg242.user_role");
//        userDao.anyNativeQuery("drop table if exists ianoleg242.roles");
//
//        userDao.anyNativeQuery("create table ianoleg242.users(" +
//                                            "id       bigint auto_increment primary  key," +
//                                            "name     varchar(255) not null," +
//                                            "lastName varchar(255) not null," +
//                                            "age      tinyint      not null," +
//                                            "password varchar(255) null)");
//
//        userDao.anyNativeQuery("create table ianoleg242.roles (" +
//                                            "id   bigint auto_increment primary key," +
//                                            "role varchar(255) null," +
//                                            "constraint UK_sgksp4dwl2848ia9w5vb3b121 unique (role))");
//
//        userDao.anyNativeQuery("create table ianoleg242.user_role(" +
//                                            "user_id bigint not null," +
//                                            "role_id bigint not null," +
//                                            "constraint user_role_roles_id_fk" +
//                                            "        foreign key (role_id) references roles (id)," +
//                                            "constraint FKm844b5vqh44ddxv7duo5e12ao" +
//                                            "        foreign key (role_id) references roles (id)," +
//                                            "constraint user_role_users_id_fk" +
//                                            "        foreign key (user_id) references users (id))");


//        Role roleAdmin = new Role("ROLE_ADMIN");
//        Role roleUser  = new Role("ROLE_USER");
//
//        roleService.saveRole(roleAdmin);
//        roleService.saveRole(roleUser);
//
//        User userIvanov = new User("Ivan Ivanov", "321", "i");
//        User userPetrov = new User("Petr Petrov", "321", "p");
//
//        userIvanov.addRole(roleAdmin);
//        userIvanov.addRole(roleUser);
//
//        userPetrov.addRole(roleUser);
//
//        userService.saveUser(userIvanov);
//        userService.saveUser(userPetrov);
    }
}
