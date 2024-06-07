package com.jamsirat.atmapi.event;


import com.jamsirat.atmapi.model.User;
import com.jamsirat.atmapi.repository.ITokenRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
@Slf4j
@RequiredArgsConstructor
@EnableAsync
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final ITokenRepository tokenRepository;
    private final JavaMailSender mailSender;
    private  User user;


    @Override
    @Async
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        user = event.getUser();
        var verificationTokens = tokenRepository.findAllByValidToken(user.getId());
        String verificationToken = verificationTokens.stream().map(x-> x.getToken()).findFirst().get();
        String url = event.getApplicationUrl() + "/auth/v1/register/verifyEmail?token=" + verificationToken;

        try {
            sendVerificationEmail(url);
        } catch (MessagingException  | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        log.info("Click the link to verify your registration {}",url);
    }


    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String sender = "User Registration Portal";
        String mailContent = "<p> Hi, "+ user.getFirstName()+ ", </p>"+
                "<p>Thank you for registering with us,"+"" +
                "Please, follow the link below to complete your registration.</p>"+
                "<a href=\"" +url+ "\">Verify your email to activate your account</a>"+
                "<p> Thank you <br> Users Registration Portal Service";

        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("iamfaqih.rochman@gmail.com",sender);
        messageHelper.setTo(user.getUsername());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }

}