package com.oschepich.spring_security.controler;

import com.oschepich.spring_security.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping (value = "/user")
    public String showUser(Model model, Principal principal) {
        model.addAttribute("user", this.userDao.getUserByUsername(principal.getName()));
        return "user";
    }
}
