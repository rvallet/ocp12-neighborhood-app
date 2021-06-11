package com.neighborhood.msbatch.service.impl;

import com.neighborhood.msbatch.pojo.Mail;
import com.neighborhood.msbatch.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmail(Mail email) {
        LOGGER.info("sending email from {} to {}", email.getFrom(), email.getTo());
        //TODO : make the job
    }
}
