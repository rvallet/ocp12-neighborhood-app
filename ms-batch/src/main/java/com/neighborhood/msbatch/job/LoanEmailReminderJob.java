package com.neighborhood.msbatch.job;

import com.neighborhood.msbatch.service.EmailService;
import com.neighborhood.msbatch.service.LoanEmailReminderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class LoanEmailReminderJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanEmailReminderJob.class);

    @Autowired
    EmailService emailService;

    @Autowired
    LoanEmailReminderService loanEmailReminderService;

    // Manual launch with wget "http://localhost:9095/feedLoanEmailReminderRepository"
    @Scheduled(cron="0 0/1 * * * ?")
    //@Scheduled(cron="0 0 3 * * ?")
    public void feedLoanEmailReminderRepository(){
        long t1 = System.currentTimeMillis();
        LOGGER.info("Start Job - feedLoanEmailReminderRepository");

        loanEmailReminderService.feedLoanEmailReminderRepository();

        long t2 = System.currentTimeMillis();
        LOGGER.info("End Job -feedLoanEmailReminderRepository ({} ms)", t2-t1);
    }

    // Manual launch with wget "http://localhost:9095/launchLoanEmailReminder"
    @Scheduled(cron="0/30 * * * * ?")
    //@Scheduled(cron="0 0 8 * * ?")
    public void doJob(){
        long t1 = System.currentTimeMillis();
        LOGGER.info("Start Job - launchLoanEmailReminder");

        emailService.sendLoanReminderEmail();

        long t2 = System.currentTimeMillis();
        LOGGER.info("End Job - launchLoanEmailReminder ({} ms)", t2-t1);
    }




}
