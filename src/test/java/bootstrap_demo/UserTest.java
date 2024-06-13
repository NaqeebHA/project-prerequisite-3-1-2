package bootstrap_demo;

import bootstrap_demo.model.Role;
import bootstrap_demo.model.User;
import bootstrap_demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

@SpringBootTest
public class UserTest {

    @Autowired
    UserService userService;

    @Test
    public void testUser() throws Exception {
        User user = userService.getUserById(2L);

            Assert.isTrue(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")), "user doesn't have admin role");
//            System.out.println("Hello World");

    }
}
