package com.timdiadiem.controller;

import com.timdiadiem.service.pkInterface.HotelService;
import com.timdiadiem.service.pkInterface.TourService;
import com.timdiadiem.service.pkInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    HotelService hotelService;
    @Autowired
    TourService tourService;
    @Autowired
    UserService userService;
    @GetMapping
    public ModelAndView admin() {
        return new ModelAndView("views-admin/hotel-edit");
    }

    @GetMapping("/edit-hotel")
    public ModelAndView editHotel(@PageableDefault(size = 2) Pageable pageable) {
        return new ModelAndView("views-admin/hotel-edit",  "listHotel", hotelService.findAll(pageable));
    }

    @GetMapping("/edit-tour")
    public ModelAndView editTour() {
        return new ModelAndView("views-web/listtour");
    }

    @GetMapping("/edit-user")
    public ModelAndView editUser() {
        return new ModelAndView("views-web/blog-single");
    }

    @GetMapping("/edit-blog")
    public ModelAndView editBlog() {
        return new ModelAndView("views-web/blog");
    }

}
