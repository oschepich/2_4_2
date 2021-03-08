package com.oschepich.spring_security.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView showUser() {
        return new ModelAndView("user");
    }
}
