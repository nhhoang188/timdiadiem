package com.timdiadiem.controller;

import com.timdiadiem.service.email.RegistrationRequest;
import com.timdiadiem.service.email.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

//    @GetMapping
//    public String showRegistrationForm(){
//    }
    @PostMapping
    public String register(@Valid @RequestBody RegistrationRequest registrationRequest){
        return registrationService.register(registrationRequest);
    }
    @GetMapping("/confirm")
    public String confirm(@RequestParam String token){
        registrationService.confirmToken(token);
        return "registrationSuccess";
    }
}
