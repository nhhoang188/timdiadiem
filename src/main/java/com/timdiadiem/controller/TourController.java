package com.timdiadiem.controller;

import com.timdiadiem.model.Comment;
import com.timdiadiem.model.Tour;
import com.timdiadiem.model.User;
import com.timdiadiem.service.email.UserService;
import com.timdiadiem.service.pkInterface.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/tours")
public class TourController {
    @Autowired
    TourService tourService;
    @Autowired
    HotelService hotelService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    @Autowired
    ConvenientService convenientService;
    @Autowired
    RoomService roomService;
    @GetMapping
    public ModelAndView hotel(@PageableDefault(size = 6) Pageable pageable, @RequestParam("name") Optional<String> name, @RequestParam("price") Optional<Double> price, @RequestParam("city") Optional<Long> city, Principal principal) {
        Page<Tour> tours;

        boolean search =name.isPresent() && price.isPresent() && city.isPresent() ;
        if (search && !name.get().equals("") ){
            tours = tourService.findTourByNameAndPriceAndCity(name.get(), price.get(), city.get(), pageable);
        }
        else if (price.isPresent()) {
            tours = tourService.findTourByPriceLessThanEqual(price.get(), pageable);
        }  else if (name.isPresent() && !name.get().equals("")){
            tours = tourService.findAllByNameContaining(name.get(), pageable);
        } else if (city.isPresent()){
            tours = tourService.findTourByCity(city.get(),pageable);
        }
        else {
            tours = tourService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("views-web/listtour");
        if (principal != null){
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            modelAndView.addObject( "user", user);
        }
        modelAndView.addObject("hotels", tours);
        return modelAndView;

    }

    @GetMapping("/{id}")
    public ModelAndView hotel(@PathVariable Long id, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("/views-web/tour-info");
        modelAndView.addObject("hotel", tourService.findById(id));
        modelAndView.addObject("listcomment", commentService.findCommentByTourId(id));
        modelAndView.addObject("comment", new Comment());
        if (principal != null) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            modelAndView.addObject("user", user);
        }
        return modelAndView;
    }
}
