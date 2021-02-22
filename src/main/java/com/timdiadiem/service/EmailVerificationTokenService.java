package com.timdiadiem.service;

import com.timdiadiem.model.EmailVerificationToken;
import com.timdiadiem.repository.EmailVerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmailVerificationTokenService implements iEmailVerificationTokenService{
    @Autowired
    private EmailVerificationTokenRepository emailVerificationTokenRepository;

    public void saveToken(EmailVerificationToken emailVerificationToken){
        emailVerificationTokenRepository.save(emailVerificationToken);
    }

    public Optional<EmailVerificationToken> getToken(String token){
        return emailVerificationTokenRepository.findByToken(token);
    }

    @Override
    public void verifyToken(String token) {
        emailVerificationTokenRepository.updateVerifiedAt(LocalDateTime.now(), token);
    }
}
