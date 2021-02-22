package com.timdiadiem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/profile")
public class UserController {
    @GetMapping("/{userID}")
    public ModelAndView showUserInfo(@PathVariable Long userID){
        return new ModelAndView("");
    }
}
