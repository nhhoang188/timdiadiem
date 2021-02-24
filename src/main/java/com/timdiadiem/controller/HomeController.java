package com.timdiadiem.controller;

import com.timdiadiem.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public ModelAndView home(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("/views-web/index");
        if (principal != null){
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            modelAndView.addObject( "user", user);
        }
        return modelAndView;
    }
    @GetMapping("/contact")
    public String contactPage(){
        return "/views-web/contact";
    }
}
