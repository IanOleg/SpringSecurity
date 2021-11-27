import com.crud.config.AppConfig;
import com.crud.dao.UserDao;
import com.crud.dao.UserDaoImp;
import com.crud.model.Role;
import com.crud.model.User;
import com.crud.service.UserService;
import com.crud.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

public class Test {

    @Autowired
    static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    public static void main(String[] args) {

        UserService userService = context.getBean(UserService.class);

        User user = userService.getUser(1);
        User user1 = userService.getUser(1, true);

        System.out.println(user);
        System.out.println(user1);
        //User user = new User("name", "lastnmae", (byte) 1);

        //Set<Role> setRole = user.getRoles();
        //setRole.add(new Role(3, "ROLE_DBA"));
        //user.setRoles(setRole);

        //userService.saveUser(user);
    }
}

