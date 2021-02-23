package com.timdiadiem.controller;

import com.timdiadiem.model.Hotel;
import com.timdiadiem.service.pkInterface.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    @GetMapping
    public ModelAndView hotel(@PageableDefault(size = 3) Pageable pageable) {
        Page<Hotel> hotels = hotelService.findAll(pageable);
        return new ModelAndView("/views/test","hotels",hotels);

    }
    @GetMapping("/{id}")
    public ModelAndView hotel(@PathVariable Long id) {
        return new ModelAndView("/views/test", "hotels", hotelService.findById(id));
    }

}
