package com.oschepich.spring_security.controler;


import com.oschepich.spring_security.dao.UserDao;
import com.oschepich.spring_security.model.Role;
import com.oschepich.spring_security.model.User;
import com.oschepich.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
//@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model, @AuthenticationPrincipal User user, Principal principal) {
        List<User> users = userService.getAllUser();
        model.addAttribute("allUser", users);
        return "admin";
    }
//    @PostMapping("/add")
//    public String addUser(@ModelAttribute("user") User user, @RequestParam(value = "roles") String[] roles) {
//        Set<Role> set = new HashSet<>();
//        for (String role : roles){
//            if (roles.length == 0) {
//                set.add(this.roleService.findByRole("ROLE_USER"));
//            } else {
//                set.add(this.roleService.findByRole(r));
//            }
//        }
//        user.setRole(set);
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }
//


//    @GetMapping("/edit/{id}")
//    public ModelAndView getUserToEdit(@PathVariable Long id) {
//        User user = (User) userService.show(id);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/admin/edit/{id}");
//        modelAndView.addObject("user", user);
//        return modelAndView;
//    }
//    @PostMapping("/edit/{id}")
//    public ModelAndView editUser(@PathVariable Long id, HttpServletRequest req) {
//        String name = req.getParameter("name");
//        String email = req.getParameter("email");
//        userService.updateUser(id, name, email);
//        return new ModelAndView("redirect:/admin");
//    }

    @PostMapping("/admin/delete/{id}")
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

}

