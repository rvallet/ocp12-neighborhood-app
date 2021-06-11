package com.neighborhood.msbatch.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="loan_email_reminder")
public class LoanEmailReminder implements Serializable {

    @Id
    @Column(name="id_loan_email_reminder")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String userEmail;

    private String userLastname;

    private String userFirstname;

    private Long ownerId;

    private Long loanId;

    private String loanTitle;

    private Date endLoan;

    private Date sendingEmailDate;

    private Boolean isEmailSent;

    public LoanEmailReminder(){
        super();
        this.isEmailSent = false;
    }

    public LoanEmailReminder(Long userId, String userEmail, String userLastname, String userFirstname,
                             Long ownerId, Long loanId, String loanTitle, Date endLoan) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userLastname = userLastname;
        this.userFirstname = userFirstname;
        this.ownerId = ownerId;
        this.loanId = loanId;
        this.loanTitle = loanTitle;
        this.endLoan = endLoan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getLoanTitle() {
        return loanTitle;
    }

    public void setLoanTitle(String loanTitle) {
        this.loanTitle = loanTitle;
    }

    public Date getEndLoan() {
        return endLoan;
    }

    public void setEndLoan(Date endLoan) {
        this.endLoan = endLoan;
    }

    public Date getSendingEmailDate() {
        return sendingEmailDate;
    }

    public void setSendingEmailDate(Date sendingEmailDate) {
        this.sendingEmailDate = sendingEmailDate;
    }

    public Boolean getEmailSent() {
        return isEmailSent;
    }

    public void setEmailSent(Boolean emailSent) {
        isEmailSent = emailSent;
    }
}
