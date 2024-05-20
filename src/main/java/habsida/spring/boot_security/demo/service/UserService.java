package habsida.spring.boot_security.demo.service;

import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface UserService {

    public void addUser(User user);

    public List<User> getUserList();

    public User getUserById(Long id) throws Exception;

    public User getUserByEmail(String email);

    public void updateUserById(Long id, String firstName, String lastName, String email, String password, Set<Role> roles);

    public void deleteUserById(Long id);
}
