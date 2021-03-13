package com.timdiadiem.controller;

import com.timdiadiem.model.User;
import com.timdiadiem.service.pkInterface.BookingService;
import com.timdiadiem.service.pkInterface.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class UserController {
    @Autowired
    TourService tourService;
    @Autowired
    BookingService bookingService;

    @GetMapping()
    public ModelAndView profile(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("/views-web/user");
        if (principal != null) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            modelAndView.addObject("user", user);
        }
        return modelAndView;
    }
    @GetMapping(("/history"))
    public ModelAndView bookingHisotry(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("/views-web/bookinghistory");
        if (principal != null) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            modelAndView.addObject("user", user);
            modelAndView.addObject("history",bookingService.findBookingsByUserId(user.getId()));
        }
        return modelAndView;
    }

}
