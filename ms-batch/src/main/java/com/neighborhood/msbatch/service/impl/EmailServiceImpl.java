package com.neighborhood.msbatch.service.impl;

import com.neighborhood.msbatch.config.EmailConfig;
import com.neighborhood.msbatch.config.MailProperties;
import com.neighborhood.msbatch.pojo.Mail;
import com.neighborhood.msbatch.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    MailProperties mailProperties;

    @Override
    public void sendEmail(Mail email) {
        LOGGER.info(
                "sending email from {} to {} :\nObjet : {}\nText:{}",
                email.getFrom(),
                email.getTo(),
                email.getObjet(),
                email.getContent()
        );

        SimpleMailMessage message = emailConfig.getMessageNoReply();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getObjet());
        message.setText(email.getContent());

        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

    }
}
