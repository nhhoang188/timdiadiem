package com.timdiadiem.controller;

import com.timdiadiem.service.pkInterface.HotelService;
import com.timdiadiem.service.pkInterface.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    HotelService hotelService;
    @Autowired
    TourService tourService;
    @GetMapping
    public ModelAndView admin() {
        return new ModelAndView("views-admin/admin");
    }

    @GetMapping("/edit-hotel")
    public ModelAndView editHotel(@PageableDefault(size = 2) Pageable pageable) {
        return new ModelAndView("views-admin/hotel",  "listHotel", hotelService.findAll(pageable));
    }

    @GetMapping("/edit-tour")
    public ModelAndView editTour(@PageableDefault(size = 2) Pageable pageable) {
        return new ModelAndView("views-admin/tour");
    }

    @GetMapping("/edit-user")
    public ModelAndView editUser() {
        return new ModelAndView("views-admin/user");
    }

    @GetMapping("/edit-blog")
    public ModelAndView editBlog() {
        return new ModelAndView("views-admin/blog");
    }
    @GetMapping("/edit-booking")
    public ModelAndView editBooking() {
        return new ModelAndView("views-admin/booking");
    }

}
