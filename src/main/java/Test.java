import com.crud.config.AppConfig;
import com.crud.model.Role;
import com.crud.model.User;
import com.crud.service.RoleService;
import com.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    @Autowired
    static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    public static void main(String[] args) {

        UserService userService = context.getBean(UserService.class);
        RoleService roleService = context.getBean(RoleService.class);

        userService.anyNativeQuery("drop table if exists ianoleg242.user_role");
        userService.anyNativeQuery("drop table if exists ianoleg242.users");
        userService.anyNativeQuery("drop table if exists ianoleg242.roles");

        userService.anyNativeQuery("create table ianoleg242.users(" +
                                        "loginname varchar(255) not null primary key," +
                                        "firstname varchar(255) not null," +
                                        "lastname varchar(255) not null," +
                                        "phonenumber varchar(255) not null," +
                                        "password varchar(255) null)");

        userService.anyNativeQuery("create table roles(" +
                                            "role varchar(255) not null primary key)");

        userService.anyNativeQuery("create table user_role(" +
                    "loginname varchar(255) not null," +
                    "role      varchar(255) not null," +
                    "primary key (loginname, role)," +
                    "constraint FK3q66atonv6gbp10chtg5ogetv" +
                    "    foreign key (loginname) references users (loginname)," +
                    "constraint FKk4riu5jjrbgbvtu6fqqtsy62u" +
                    "     foreign key (role) references roles (role))");

        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser  = new Role("ROLE_USER");
        Role roleManager = new Role("ROLE_MANAGER");

        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);
        roleService.saveRole(roleManager);

        User userIvanov = new User("i", "321", "Ivan", "Ivanov","02");
        User userPetrov = new User("p", "321", "Petr", "Petrov","03");

        userIvanov.addRole(roleAdmin);
        userIvanov.addRole(roleUser);

        userPetrov.addRole(roleUser);

        userService.saveUser(userIvanov);
        userService.saveUser(userPetrov);
    }
}

