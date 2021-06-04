package com.neighborhood.msneighborhood.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neighborhood.msneighborhood.enumerated.LoanStatusEnum;
import com.neighborhood.msneighborhood.utils.DateTools;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="loan")
public class Loan implements Serializable {

    @Id
    @Column(name="id_loan")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Date startLoan;

    private Date endLoan;

    private Date returnLoan;

    private String loanStatus;

    private String ownerFullName;

    private Long ownerId;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonBackReference
    private User user;

    public Loan() {
        super();
        this.startLoan = new Date();
        this.loanStatus = LoanStatusEnum.IN_PROGRESS.toString();
    }

    public Loan(User user) {
        this.user = user;
        this.startLoan = new Date();
        this.endLoan = DateTools.addDays(new Date(), 7);
        this.loanStatus = LoanStatusEnum.IN_PROGRESS.toString();
    }

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

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public void setOwnerFullName(String ownerFullName) {
        this.ownerFullName = ownerFullName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id) && Objects.equals(title, loan.title) && Objects.equals(startLoan, loan.startLoan) && Objects.equals(endLoan, loan.endLoan) && Objects.equals(returnLoan, loan.returnLoan) && Objects.equals(loanStatus, loan.loanStatus) && Objects.equals(ownerFullName, loan.ownerFullName) && Objects.equals(ownerId, loan.ownerId) && Objects.equals(user, loan.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, startLoan, endLoan, returnLoan, loanStatus, ownerFullName, ownerId, user);
    }
}
