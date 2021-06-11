package com.neighborhood.msbatch.ws.controller;

import com.neighborhood.msbatch.api.ApiRegistration;
import com.neighborhood.msbatch.pojo.Mail;
import com.neighborhood.msbatch.service.EmailService;
import com.neighborhood.msbatch.ws.exception.NoSuchResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    EmailService emailService;

    @PostMapping(value = ApiRegistration.REST_SEND_EMAIL)
    public void sendEmail(@RequestBody Mail email) {
        if (email==null) throw new NoSuchResultException("Demande d'envoi d'email : ECHEC");
        LOGGER.info("Reception d'une demande d'envoie d'email {}", email);
        emailService.sendEmail(email);
    }
}
