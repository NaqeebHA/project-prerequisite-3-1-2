package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.repo.RoleRepository;
import habsida.spring.boot_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegistrationForm", new User());
//        List<Role> roles = (List<Role>) roleRepository.findAll();
//        model.addAttribute("allRoles", roles);
        return "registration";
    }

    @PostMapping("/registered")
    public String registerUser(@Valid @ModelAttribute("userRegistrationForm") User newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        String encodedPassword = encoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        Role userRole = roleRepository.findByRole("USER").get();
        List<Role> newRole = List.of(userRole);
        newUser.setRoles(new HashSet<Role>(newRole));
        userService.addUser(newUser);
        return "register_success";
    }
}
