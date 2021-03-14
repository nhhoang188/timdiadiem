package com.timdiadiem.security;

import com.timdiadiem.model.User;
import com.timdiadiem.model.UserRole;
import com.timdiadiem.service.email.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/about", "/contact", "/css/**", "/js/**", "/blogs/**", "/images/**", "/hotels/**", "/tours/**", "/profile/**", "/register/**").permitAll()
                .antMatchers("/blogs/add").hasAnyAuthority(UserRole.MEMBER.name(), UserRole.ADMIN.name())
                .antMatchers("/blogs/**").permitAll().antMatchers("/static/**").permitAll().antMatchers("/templates/**").permitAll().
                antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/icon/**","/assets/**", "/dist/**", "/js.js/**", "/scss/**", "/flaticon/**").permitAll().
                antMatchers("/booking/showbookingform", "/booking/checkavailability").hasAnyAuthority(UserRole.MEMBER.name(), UserRole.ADMIN.name())
                .antMatchers("/booking/**").permitAll()
                .antMatchers("/admin/").hasAuthority(UserRole.ADMIN.name()).anyRequest().hasAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login").permitAll().defaultSuccessUrl("/", true)
                .and().rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(10)).userDetailsService(userService)
                .and().logout()
                .logoutUrl("/logout").logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/")
        ;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder.bCryptPasswordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }

}
