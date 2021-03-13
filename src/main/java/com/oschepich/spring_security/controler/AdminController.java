package com.oschepich.spring_security.controler;


import com.oschepich.spring_security.model.Role;
import com.oschepich.spring_security.model.User;
import com.oschepich.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    //вывожу всех пользователей
    @GetMapping
    public ModelAndView getAllUsers(ModelAndView modelAndView) {
        modelAndView.addObject("allUser", userService.getAllUser());
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("admin.html");
        return modelAndView;
    }

    @GetMapping("/new")
    public String addNewUser(Model model) {
//        model.addAttribute("listRole", userService.getListRole());
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam(value = "roleSet") String [] roles) {
        user.setRole(getAddRole(roles));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getUserToEdit(@PathVariable Long id) {
        User user = (User) userService.show(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @PostMapping("/edit/{id}")
    public String editUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "roleSet") String [] roles) {
        user.setRole(getAddRole(roles));
        userService.creatUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
//    @GetMapping("/admin/create")
//    public ModelAndView getFormForCreateUser() {
//        return new ModelAndView("create");
//    }
//
//    @PostMapping("/admin/create")
//    public ModelAndView createUser(HttpServletRequest req) {
//        String username = req.getParameter("name");
//        String email = req.getParameter("email");
//        String password = req.getParameter("password");
//        userService.creatUser(new User(username, email, password));
//        return new ModelAndView( "redirect:/admin");
//    }

    private Set<Role> getAddRole(String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(userService.getRoleByName(role));
        }
        return roleSet;
    }

}

