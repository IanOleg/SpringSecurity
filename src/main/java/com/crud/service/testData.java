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

    @PostConstruct
    void testData(){

        userService.anyNativeQuery("drop table if exists ianoleg242.user_role");
        userService.anyNativeQuery("drop table if exists ianoleg242.users");
        userService.anyNativeQuery("drop table if exists ianoleg242.roles");

        userService.anyNativeQuery("create table ianoleg242.users(" +
                "id       bigint auto_increment primary  key," +
                "name     varchar(255) not null," +
                "loginName varchar(255) not null," +
                "password varchar(255) null)");

        userService.anyNativeQuery("create table ianoleg242.roles (" +
                "id   bigint auto_increment primary key," +
                "role varchar(255) null," +
                "constraint UK_sgksp4dwl2848ia9w5vb3b121 unique (role))");

        userService.anyNativeQuery("create table ianoleg242.user_role(" +
                "user_id bigint not null," +
                "role_id bigint not null," +
                "constraint user_role_roles_id_fk" +
                "        foreign key (role_id) references roles (id)," +
                "constraint FKm844b5vqh44ddxv7duo5e12ao" +
                "        foreign key (role_id) references roles (id)," +
                "constraint user_role_users_id_fk" +
                "        foreign key (user_id) references users (id))");

        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser  = new Role("ROLE_USER");

        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);

        User userIvanov = new User("Ivan Ivanov", "321", "i");
        User userPetrov = new User("Petr Petrov", "321", "p");

        userIvanov.addRole(roleAdmin);
        userIvanov.addRole(roleUser);

        userPetrov.addRole(roleUser);

        userService.saveUser(userIvanov);
        userService.saveUser(userPetrov);
    }
}
