package com.timdiadiem.controller;

import com.timdiadiem.model.Hotel;
import com.timdiadiem.model.User;
import com.timdiadiem.service.email.UserService;
import com.timdiadiem.service.pkInterface.HotelService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    HotelService hotelService;
    @Autowired
    UserService userService;
    @GetMapping
    public ModelAndView hotel(@PageableDefault(size = 3) Pageable pageable) {
        Page<Hotel> hotels = hotelService.findAll(pageable);
        return new ModelAndView("/views-web/listhotel","hotels",hotels);

    }
    @GetMapping("/{id}")
    public ModelAndView hotel(@PathVariable Long id, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("/views-web/hotel-info");
        modelAndView.addObject("hotel", hotelService.findById(id));
        if (principal != null){
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            modelAndView.addObject( "user", user);
        }
        return modelAndView;
    }

}
