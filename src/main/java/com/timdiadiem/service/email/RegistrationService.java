package com.timdiadiem.service.email;

import com.timdiadiem.model.EmailVerificationToken;
import com.timdiadiem.model.User;
import com.timdiadiem.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationService implements iRegistrationService{
    @Autowired
    private iUserService userService;
    @Autowired
    private iEmailVerificationTokenService emailVerificationTokenService;
    @Autowired
    private SendEmailService sendEmailService;

    @Override
    public String register(RegistrationRequest registrationRequest) {
        String token = userService.signUpUser(
                new User(registrationRequest.getFirstName(),
                        registrationRequest.getLastName(),
                        registrationRequest.getUsername(),
                        registrationRequest.getPassword(),
                        registrationRequest.getEmail(),
                        registrationRequest.getPhone(),
                        registrationRequest.getAddress(),
                        UserRole.MEMBER
                ));
        String verificationLink = "http://localhost:8080/register/confirm?token=" + token;

        String emailTo = registrationRequest.getEmail();
        String body = "Please verify your account by clicking this link " + verificationLink;
        String title = "Verify timdiadiem account";
        sendEmailService.sendEmail(emailTo,body,title);
        return "registered";
    }

    public String confirmToken(String token){
        EmailVerificationToken verificationToken
                = emailVerificationTokenService.getToken(token)
                .orElseThrow(() ->new IllegalStateException("token not found"));
                                                ;
        if (verificationToken.getVerifiedAt() != null){
            throw new IllegalStateException("email confirmed already");
        }

        LocalDateTime expiredAt = verificationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }

        emailVerificationTokenService.verifyToken(token);

        userService.enableUser(verificationToken.getUser().getEmail());

        return "verified";
    }

}
