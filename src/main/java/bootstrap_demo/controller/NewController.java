//package bootstrap_demo.controller;
//
//import bootstrap_demo.dto.UserDTO;
//import bootstrap_demo.model.Role;
//import bootstrap_demo.model.User;
//import bootstrap_demo.repo.RoleRepository;
//import bootstrap_demo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//import bootstrap_demo.model.Role;
//import bootstrap_demo.model.User;
//import bootstrap_demo.repo.RoleRepository;
//import bootstrap_demo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@Controller
//public class NewController {
//
//        @Autowired
//        UserService userService;
//        @Autowired
//        RoleRepository roleRepository;
//        @Autowired
//        BCryptPasswordEncoder encoder;
//
//        @GetMapping("")
//        public String adminHomePage(Model model) {
//            model.addAttribute("userForm", new User());
//            model.addAttribute("users", userService.getUserList());
//            return "admin";
//        }
//
//        @GetMapping("/add_user")
//        public String addUser(Model model) {
//            model.addAttribute("userForm", new User());
//            List<Role> roles = (List<Role>) roleRepository.findAll();
//            model.addAttribute("allRoles", roles);
//            return "add_user";
//        }
//
//        @PostMapping("/added")
//        public String addUser(@Valid @ModelAttribute("userForm") User newUser, BindingResult bindingResult, Model model) {
//            if (bindingResult.hasErrors()) {
//                List<Role> roles = (List<Role>) roleRepository.findAll();
//                model.addAttribute("allRoles", roles);
//                return "add_user";
//            }
//            String encodedPassword = encoder.encode(newUser.getPassword());
//            newUser.setPassword(encodedPassword);
//            userService.addUser(newUser);
//            model.addAttribute("userForm", new User());
//            model.addAttribute("users", userService.getUserList());
//            return "redirect:/admin";
//        }
//
//
//        @GetMapping("/edit")
//        public String editUser(@RequestParam("id") @ModelAttribute Long id, Model model) throws Exception {
//            User userToEdit = userService.getUserById(id);
//            model.addAttribute("editUserForm", userToEdit);
//            model.addAttribute("user", userToEdit);
//            model.addAttribute("id", id);
//            List<Role> roles = (List<Role>) roleRepository.findAll();
//            model.addAttribute("allRoles", roles);
//            return "edit";
//        }
//
//    @PostMapping("/edited")
//    public String postEditUser(@Valid @ModelAttribute("editUserForm") UserDTO newUser, BindingResult bindingResult,
//                               @RequestParam("id") @ModelAttribute Long id, Model model) throws Exception {
//        User user = userService.getUserById(id);
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("id", id);
//            model.addAttribute("user", user);
//            List<Role> roles = (List<Role>) roleRepository.findAll();
//            model.addAttribute("allRoles", roles);
//            return "edit";
//        }
//
//        if (newUser.getPassword().isEmpty()) {
//            newUser.setPassword(user.getPassword());
//        } else {
//            newUser.setPassword(encoder.encode(newUser.getPassword()));
//        }
//        user.fromUserDTO(newUser);
//        userService.updateUser(user);
//        return "redirect:/admin?id=" + id.toString();
//    }
//
//        @GetMapping("/delete")
//        public String deleteUser(@RequestParam("id") Long id, Model model) {
//            userService.deleteUserById(id);
//            model.addAttribute("userForm", new User());
//            model.addAttribute("users", userService.getUserList());
//            return "redirect:/admin";
//        }
//
//}
