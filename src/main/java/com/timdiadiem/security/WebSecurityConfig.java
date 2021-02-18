//package com.timdiadiem.security;
//
//import com.timdiadiem.service.UserService;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//@EnableWebSecurity
//@AllArgsConstructor
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    private UserService userService;
//    private PasswordEncoder passwordEncoder;
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .csrf().disable().
////
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder.bCryptPasswordEncoder());
//        provider.setUserDetailsService(userService);
//        return provider;
//    }
//}
