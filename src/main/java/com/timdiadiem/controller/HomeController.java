package com.timdiadiem.controller;

import com.timdiadiem.model.User;
import com.timdiadiem.service.pkInterface.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    TourService tourService;
    @GetMapping
    public ModelAndView home(Principal principal, @PageableDefault(size = 6) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/views-web/index");
        if (principal != null){
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            modelAndView.addObject( "user", user);
        }
        modelAndView.addObject("listtour",tourService.findAll(pageable));
        return modelAndView;
    }
    @GetMapping("/contact")
    public String contactPage(){
        return "user";
    }
}
