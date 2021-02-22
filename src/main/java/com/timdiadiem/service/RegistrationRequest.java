package com.timdiadiem.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
//public class RegistrationRequest implements Validator {
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    @Email
    private String email;
    private String phone;
    private String address;
    //TODO validate request
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return RegistrationRequest.class.isAssignableFrom(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        System.out.println("abc");
//    }
}
