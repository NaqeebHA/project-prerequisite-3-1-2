package bootstrap_demo.controller;

import bootstrap_demo.model.User;
import bootstrap_demo.service.AuthenticationService;
import bootstrap_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")

public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;

    @ModelAttribute
    public void addAttributes(Model model) {
        if (authenticationService.getUser() != null) {
            model.addAttribute("userDetails", authenticationService.getUser());
            Set<String> roles = AuthorityUtils.authorityListToSet(authenticationService.getUser().getAuthorities());
            if (authenticationService.getUser().getAuthorities() != null) {
                model.addAttribute("role_admin", roles.contains("ROLE_ADMIN"));
                model.addAttribute("role_user", roles.contains("ROLE_USER"));
            }
        }
    }

    @GetMapping("")
    public String index(Model model) {
        return "user";
    }

}


