package bootstrap_demo.service;

import bootstrap_demo.model.Role;
import bootstrap_demo.model.User;

import java.util.List;
import java.util.Set;


public interface UserService {

    public void addUser(User user);

    public List<User> getUserList();

    public User getUserById(Long id) throws Exception;

    public User getUserByEmail(String email);

    public void updateUser(User user);

    public void deleteUserById(Long id);
}
