package com.timdiadiem.controller;

import com.timdiadiem.model.BlogCategory;
import com.timdiadiem.model.User;
import com.timdiadiem.service.pkInterface.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogCategoryService blogCategoryService;
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

    @GetMapping("/verify-blog")
    public void verifyBlog(@RequestParam Long blogId) {
        blogService.verifyBlog(blogId);
    }

    @GetMapping("/blogs")
    public ModelAndView showAllBLogs(){
        return new ModelAndView();
    }

    @PostMapping("/blogs/categories/add")
    public void addCategories(@ModelAttribute BlogCategory blogCategory){
        blogCategoryService.save(blogCategory);
    }

}
