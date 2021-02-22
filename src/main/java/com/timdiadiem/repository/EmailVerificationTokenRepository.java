package com.timdiadiem.repository;

import com.timdiadiem.model.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional
public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken,Long> {
    public Optional<EmailVerificationToken> findByToken(String token);

    @Modifying
    @Query("UPDATE EmailVerificationToken a SET a.verifiedAt = ?1 WHERE a.token = ?2")
    public int updateVerifiedAt(LocalDateTime now, String token);
}
