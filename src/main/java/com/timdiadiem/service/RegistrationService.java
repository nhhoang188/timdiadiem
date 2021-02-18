package com.timdiadiem.service;

import org.springframework.stereotype.Service;

@Service
public class RegistrationService implements iRegistrationService{
    @Override
    public String register() {
        return "registered";
    }
}
