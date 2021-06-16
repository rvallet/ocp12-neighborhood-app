package com.neighborhood.msbatch.service;

import com.neighborhood.msbatch.beans.LoanBean;
import com.neighborhood.msbatch.entities.LoanEmailReminder;

import java.util.List;

public interface LoanEmailReminderService {

    List<LoanBean> getLoanList();

    void feedLoanEmailReminderRepository();

    void saveLoanEmailReminderList(List<LoanEmailReminder> loanEmailReminderList);

    List<LoanEmailReminder> findLoanEmailRemindersByIsEmailSentIsNot(Boolean isEmailSent);

    void saveLoanEmailReminder(LoanEmailReminder loanEmailReminder);
}
