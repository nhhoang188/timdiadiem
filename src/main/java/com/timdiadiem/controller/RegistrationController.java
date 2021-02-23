package com.timdiadiem.controller;

import com.timdiadiem.service.email.RegistrationRequest;
import com.timdiadiem.service.email.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;


    @RequestMapping( method = RequestMethod.GET)
    public ModelAndView showRegistrationForm() {
        ModelAndView mav = new ModelAndView("/views-web/register");
        mav.addObject("registeruser", new RegistrationRequest());
        return mav;
    }

    @RequestMapping( method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createSmartphone(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return registrationService.register(registrationRequest);
    }
    @GetMapping("/confirm")
    public String confirm(@RequestParam String token){
        registrationService.confirmToken(token);
        return "registrationSuccess";
    }

}
