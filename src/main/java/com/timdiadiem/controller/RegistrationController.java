package com.timdiadiem.controller;

import com.timdiadiem.model.User;
import com.timdiadiem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserService userService;
    @GetMapping
    public ModelAndView showRegistrationForm(){
        return new ModelAndView("registration","user",new User());
    }
//    @PostMapping
//    public String register(@RequestBody ){
//
//    }
}
