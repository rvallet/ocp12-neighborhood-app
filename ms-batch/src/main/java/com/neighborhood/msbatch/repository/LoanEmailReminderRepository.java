package com.neighborhood.msbatch.repository;

import com.neighborhood.msbatch.entities.LoanEmailReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanEmailReminderRepository extends JpaRepository<LoanEmailReminder, Long> {

    List<LoanEmailReminder> findAll();
    LoanEmailReminder findLoanEmailReminderById(Long id);

    @Query("SELECT ler FROM LoanEmailReminder ler WHERE ler.isEmailSent NOT IN (:isEmailSent)")
    List<LoanEmailReminder> findLoanEmailReminderByEmailSentIsNot(Boolean isEmailSent);

    List<LoanEmailReminder> findLoanEmailRemindersByLoanIdIs(Long loanId);


}
