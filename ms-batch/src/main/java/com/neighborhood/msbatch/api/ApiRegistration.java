package com.neighborhood.msbatch.api;

public interface ApiRegistration {

    /* Service (Commons) */
    String SERVICE_ID = "NEIGHBORHOOD";
    String REST_PREFIX = "/ms-batch";
    String REST_PAGINATION = "/page";

    /* EMAIL */
    String REST_SEND_EMAIL = "/sendMail";

    /* JOBS */
    String FEED_LOAN_EMAIL_REMINDER_DB = "/launchLoanEmailReminder";
    String LAUNCH_LOAN_EMAIL_REMINDER = "/feedLoanEmailReminderRepository";

}
