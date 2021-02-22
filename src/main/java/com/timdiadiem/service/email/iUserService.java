package com.timdiadiem.service.email;

import com.timdiadiem.model.User;

import java.util.List;
import java.util.Optional;

public interface iUserService {
    String signUpUser(User user);
    public int enableUser(String email);
    public int lockUser(String username);
    public int unlockUser(String username);

    public List<User> findAll();
    public Optional<User> findByUsername(String username);
    public void deleteUser(Long id);
}
