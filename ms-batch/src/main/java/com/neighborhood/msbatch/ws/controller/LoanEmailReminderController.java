package com.neighborhood.msbatch.ws.controller;

import com.neighborhood.msbatch.api.ApiRegistration;
import com.neighborhood.msbatch.job.LoanEmailReminderJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanEmailReminderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanEmailReminderController.class);

    @Autowired
    LoanEmailReminderJob loanEmailReminderJob;

    @GetMapping(value= ApiRegistration.LAUNCH_LOAN_EMAIL_REMINDER)
    public void launchLoanEmailReminder() {
        LOGGER.info("Reception d'une demande d'envoi d'email de rappel des outils empruntés");
        loanEmailReminderJob.doJob();
    }

    @GetMapping(value = ApiRegistration.FEED_LOAN_EMAIL_REMINDER_DB)
    public void feedLoanEmailReminderRepository(){
        LOGGER.info("Reception d'une demande d'alimentation feedLoanEmailReminder en BDD");
        loanEmailReminderJob.feedLoanEmailReminderRepository();
    }


}
