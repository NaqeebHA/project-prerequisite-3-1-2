package bootstrap_demo.controller;

import bootstrap_demo.model.User;
import bootstrap_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class PublicController {

    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
