package com.timdiadiem.controller;

import com.timdiadiem.model.Comment;
import com.timdiadiem.model.Hotel;
import com.timdiadiem.model.User;
import com.timdiadiem.service.email.UserService;
import com.timdiadiem.service.pkInterface.CommentService;
import com.timdiadiem.service.pkInterface.ConvenientService;
import com.timdiadiem.service.pkInterface.HotelService;
import com.timdiadiem.service.pkInterface.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/hotels")
public class HotelController {
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
    public ModelAndView hotel(@PageableDefault(size = 3) Pageable pageable, @RequestParam("name") Optional<String> name, @RequestParam("price") Optional<Double> price, Principal principal) {
        Page<Hotel> hotels;
        boolean search =name.isPresent() && price.isPresent();
        if (search && !name.get().equals("")){
            hotels = hotelService.findHotelByNameAndPriceContaining(name.get(), price.get(), pageable);
        }
        else if (search) {
            hotels = hotelService.findAllByNamePriceContaining(price.get(), pageable);
        }  else if (name.isPresent()){
            hotels = hotelService.findAllByNameContaining(name.get(), pageable);
        } else {
            hotels = hotelService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("views-web/listhotel");
        if (principal != null){
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            modelAndView.addObject( "user", user);
        }
        modelAndView.addObject("hotels", hotels);
        return modelAndView;

    }

    @GetMapping("/{id}")
    public ModelAndView hotel(@PathVariable Long id, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("/views-web/hotel-info");
        modelAndView.addObject("hotel", hotelService.findById(id));
        modelAndView.addObject("listcomment", commentService.findCommentByHotelId(id));
        modelAndView.addObject("listconvenient", convenientService.findConvenientByHotelId(id));
        modelAndView.addObject("comment", new Comment());
        if (principal != null) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            modelAndView.addObject("user", user);
        }
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView postCmt(@ModelAttribute("comment") Comment comment) {
        commentService.save(comment);
        return new ModelAndView("redirect:/hotels");
    }
}
