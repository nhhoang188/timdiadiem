package com.timdiadiem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public ModelAndView admin() {
        return new ModelAndView("/views/index");

    }

    @GetMapping("edit-hotel")
    public ModelAndView editHotel() {
        return null;
    }

    @GetMapping("edit-tour")
    public ModelAndView editTour() {
        return null;
    }

    @GetMapping("edit-user")
    public ModelAndView editUser() {
        return null;
    }

    @GetMapping("edit-blog")
    public ModelAndView editBlog() {
        return null;
    }
}
