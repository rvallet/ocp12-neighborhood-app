package com.neighborhood.msbatch.service;

import com.neighborhood.msbatch.pojo.Mail;

public interface EmailService {

    void sendEmail(Mail email);

    void sendLoanReminderEmail();
}
