package com.neighborhood.website.beans;

import java.util.Date;

public class LoanBean {

    private Long id;

    private String title;

    private Date startLoan;

    private Date endLoan;

    private Date returnLoan;

    private String loanStatus;

    private UserBean user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartLoan() {
        return startLoan;
    }

    public void setStartLoan(Date startLoan) {
        this.startLoan = startLoan;
    }

    public Date getEndLoan() {
        return endLoan;
    }

    public void setEndLoan(Date endLoan) {
        this.endLoan = endLoan;
    }

    public Date getReturnLoan() {
        return returnLoan;
    }

    public void setReturnLoan(Date returnLoan) {
        this.returnLoan = returnLoan;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

}
