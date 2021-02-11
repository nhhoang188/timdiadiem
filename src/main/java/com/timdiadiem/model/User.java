package com.timdiadiem.model;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Collections;

@Entity
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    @Email
    private String email;
    private String phone;
    private String address;
    private BankAcount bankAcount;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private boolean enabled;
    private boolean locked;

    // override
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority  = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    // constructors

    public User(String firstName
            , String lastName
            , String username
            , String password
            , @Email String email
            , String phone
            , String address
            , BankAcount bankAcount
            , UserRole userRole
            , boolean enabled
            , boolean locked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bankAcount = bankAcount;
        this.userRole = userRole;
        this.enabled = enabled;
        this.locked = locked;
    }
}
