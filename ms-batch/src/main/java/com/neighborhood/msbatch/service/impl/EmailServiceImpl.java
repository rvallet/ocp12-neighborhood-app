package com.neighborhood.msbatch.service.impl;

import com.neighborhood.msbatch.config.EmailConfig;
import com.neighborhood.msbatch.config.MailProperties;
import com.neighborhood.msbatch.entities.LoanEmailReminder;
import com.neighborhood.msbatch.pojo.Mail;
import com.neighborhood.msbatch.service.EmailService;
import com.neighborhood.msbatch.service.LoanEmailReminderService;
import com.neighborhood.msbatch.utils.DateTools;
import javassist.Loader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    MailProperties mailProperties;

    @Autowired
    LoanEmailReminderService loanEmailReminderService;

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

    @Override
    public void sendLoanReminderEmail() {
        List<LoanEmailReminder> loanEmailReminderList = loanEmailReminderService.findLoanEmailRemindersByIsEmailSentIsNot(true);
        LOGGER.info("loanEmailReminderList = {} (filter = {})", loanEmailReminderList.size(), "true");
        if (!CollectionUtils.isEmpty(loanEmailReminderList)) {
            for (LoanEmailReminder loanEmailReminder : loanEmailReminderList) {

                String text = String.format(
                        emailConfig.getLoanTemplate().getText(),
                        loanEmailReminder.getUserLastname(),
                        loanEmailReminder.getUserFirstname(),
                        DateTools.dateToStringPatternForEmail(loanEmailReminder.getEndLoan()),
                        loanEmailReminder.getLoanTitle()
                        );

                SimpleMailMessage message = emailConfig.getLoanTemplate();

                Mail email = new Mail();
                message.setFrom("neighbor@neighbor.com");
                message.setTo(loanEmailReminder.getUserEmail());
                message.setText(text);

                try {
                    javaMailSender.send(message);
                } catch (Exception e) {
                    LOGGER.warn(e.getMessage());
                }

                loanEmailReminder.setEmailSent(true);
                loanEmailReminder.setSendingEmailDate(new Date());
                loanEmailReminderService.saveLoanEmailReminder(loanEmailReminder);
            }
        }
    }
}
