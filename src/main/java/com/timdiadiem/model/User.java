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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    @Email
    private String email;
    private String phone;
    private String address;
    @ManyToOne
    @JoinColumn( name = "bank_id")
    private BankAcount bankAcount;
    @Enumerated(EnumType.STRING)
    private UserRole userrole;
    private boolean enabled;
    private boolean locked;

    // override
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority  = new SimpleGrantedAuthority(userrole.name());
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

    public User(String firstname
            , String lastname
            , String username
            , String password
            , @Email String email
            , String phone
            , String address
            , BankAcount bankAcount
            , UserRole userrole
            , boolean enabled
            , boolean locked) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bankAcount = bankAcount;
        this.userrole = userrole;
        this.enabled = enabled;
        this.locked = locked;
    }
}
