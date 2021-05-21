package com.neighborhood.msneighborhood.enumerated;

public enum LoanStatusEnum {
    IN_PROGRESS ("En cours"),
    CLOSED ("Terminé");

    private String loanStatus;

    LoanStatusEnum(String bookLoanStatus) {
        this.loanStatus = bookLoanStatus;
    }

    @Override
    public String toString() {
        return loanStatus;
    }
}
