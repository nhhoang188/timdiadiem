package com.timdiadiem.service;

import com.timdiadiem.model.EmailVerificationToken;

import java.time.LocalDateTime;
import java.util.Optional;

public interface iEmailVerificationTokenService {
    public void saveToken(EmailVerificationToken emailVerificationToken);
    public Optional<EmailVerificationToken> getToken(String token);
    public void verifyToken(String token);
}
