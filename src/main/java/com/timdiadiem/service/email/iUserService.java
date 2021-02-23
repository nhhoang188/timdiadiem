package com.timdiadiem.service.email;

import com.timdiadiem.model.User;

import java.util.List;
import java.util.Optional;

public interface iUserService {
    String signUpUser(User user);
    List<User> findByEnabledIsFalse();

    int enableUserByEmail(String email);

    int enableUser(String id);

    int lockUser(String username);

    int unlockUser(String username);

    List<User> findAll();

    Optional<User> findByUsername(String username);

    void deleteUser(Long id);
}
