package com.timdiadiem.controller;

import com.timdiadiem.model.Hotel;
import com.timdiadiem.model.User;
import com.timdiadiem.service.email.UserService;
import com.timdiadiem.service.pkInterface.HotelService;
import com.timdiadiem.service.pkInterface.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        return new ModelAndView("views-admin/admin", "notification", userService.findByEnabledIsFalse());
    }

    @GetMapping("/edit-hotel")
    public ModelAndView editHotel(@PageableDefault(size = 2) Pageable pageable) {
        return new ModelAndView("views-admin/hotel", "listHotel", hotelService.findAll(pageable));
    }
    @GetMapping("/edit-hotel/{id}")
    public ModelAndView edit(@PathVariable("id") Hotel hotel) {
        return new ModelAndView("views-admin/hotel-edit", "hotel", hotelService.findById(hotel.getId()));
    }

    @GetMapping("/edit-tour")
    public ModelAndView editTour(@PageableDefault(size = 2) Pageable pageable) {
        return new ModelAndView("views-admin/tour");
    }

    @GetMapping("/edit-user")
    public ModelAndView editUser() {
        return new ModelAndView("views-admin/user", "listUser", userService.findAll());
    }

    @GetMapping("/edit-user/browsing")
    public ModelAndView browsingUser() {
        return new ModelAndView("views-admin/browsing-user", "notification", userService.findByEnabledIsFalse());
    }

    @PostMapping("/edit-user/browsing")
    public ModelAndView deleteCustomer(@ModelAttribute("user") User user) {
        userService.enableUser(user.getId());
        return new ModelAndView("redirect:/admin/edit-user/browsing");
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
