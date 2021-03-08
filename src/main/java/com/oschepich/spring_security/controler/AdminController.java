package com.oschepich.spring_security.controler;


import com.oschepich.spring_security.model.Role;
import com.oschepich.spring_security.model.User;
import com.oschepich.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("allUser", users);
        return "admin";
    }
    @RequestMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "admin";
    }


    @GetMapping("/admin/edit/{id}")
    public ModelAndView getUserToEdit(@PathVariable Long id) {
        User user = (User) userService.show(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @PostMapping("/admin/edit/{id}")
    public ModelAndView editUser(@PathVariable Long id, HttpServletRequest req) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        userService.updateUser(id, name, email);
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/admin/create")
    public ModelAndView getFormForCreateUser() {
        return new ModelAndView("create");
    }

    @PostMapping("/admin/create")
    public ModelAndView createUser(HttpServletRequest req) {
        String username = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        userService.creatUser(new User(username, email, password));
        return new ModelAndView( "redirect:/admin");
    }

}

