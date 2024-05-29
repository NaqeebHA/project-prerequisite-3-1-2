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
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping("")
    public String adminHomePage(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("users", userService.getUserList());
        return "admin";
    }

    @GetMapping("/add_user")
    public String addUser(Model model) {
        model.addAttribute("userForm", new User());
        List<Role> roles = (List<Role>) roleRepository.findAll();
        model.addAttribute("allRoles", roles);
        return "add_user";
    }

    @PostMapping("/added")
    public String addUser(@Valid @ModelAttribute("userForm") User newUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Role> roles = (List<Role>) roleRepository.findAll();
            model.addAttribute("allRoles", roles);
            return "add_user";
        }
        String encodedPassword = encoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        userService.addUser(newUser);
        model.addAttribute("userForm", new User());
        model.addAttribute("users", userService.getUserList());
        return "redirect:/admin";
    }


    @GetMapping("/edit")
    public String editUser(@RequestParam("id") @ModelAttribute Long id, Model model) throws Exception {
        User user = userService.getUserById(id);
        model.addAttribute("id", id);
        model.addAttribute("user", user);
        model.addAttribute("editUserForm", user);
        List<Role> roles = (List<Role>) roleRepository.findAll();
        model.addAttribute("allRoles", roles);
        return "edit";
    }

    @PostMapping("/edited")
    public String postEditUser(@Valid @ModelAttribute("editUserForm") User newUser, BindingResult bindingResult,
                               @RequestParam("id") @ModelAttribute Long id, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("id", id);
            model.addAttribute("user", userService.getUserById(id));
            List<Role> roles = (List<Role>) roleRepository.findAll();
            model.addAttribute("allRoles", roles);
            return "edit";
        }
        String encodedPassword = encoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        userService.updateUserById(id, newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getPassword(), newUser.getRoles());
        return "redirect:/admin/edit?id=" + id.toString();
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id, Model model) {
        userService.deleteUserById(id);
        model.addAttribute("userForm", new User());
        model.addAttribute("users", userService.getUserList());
        return "redirect:/admin";
    }
}
