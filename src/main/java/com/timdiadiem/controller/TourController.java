package com.timdiadiem.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tours")
public class TourController {
    @GetMapping
    public ModelAndView hotel(@PageableDefault(size = 3) Pageable pageable) {
        return new ModelAndView("/views-web/listtour");
    }
}
