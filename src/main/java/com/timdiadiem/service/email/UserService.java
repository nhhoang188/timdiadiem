package com.timdiadiem.service.email;

import com.timdiadiem.model.EmailVerificationToken;
import com.timdiadiem.model.User;
import com.timdiadiem.repository.UserRepository;
import com.timdiadiem.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService,iUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailVerificationTokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException(username + "not found"));
    }

    public String signUpUser(User user){
        boolean emailExisted = userRepository.findByEmail(user.getEmail()).isPresent();
        if (emailExisted){
            throw new IllegalStateException("email taken");
        }

        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        EmailVerificationToken  emailVerificationToken= new EmailVerificationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        tokenService.saveToken(emailVerificationToken);

        return token;
    }

    @Override
    public List<User> findByEnabledIsFalse() {
        return userRepository.findAllByEnabledIsFalse();
    }

    @Override
    public int enableUserByEmail(String email){
        return userRepository.enableUserByEmail(email);
    }

    @Override
    public int enableUser(Long id) {
        return userRepository.enableUser(id);
    }

    @Override
    public int lockUser(String username){
        return userRepository.lockUser(username);
    }

    @Override
    public int unlockUser(String username) {
        return userRepository.unlockUser(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long id) {
         userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByid(Long id) {
        return userRepository.findById(id);
    }


}
