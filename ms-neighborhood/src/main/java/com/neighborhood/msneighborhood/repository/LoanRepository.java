package com.neighborhood.msneighborhood.repository;

import com.neighborhood.msneighborhood.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, String> {

    List<Loan> findAll();
    Loan findLoanById (Long loanId);

    /* SQL
    SELECT * FROM loan loan
    WHERE loan.id_user = 1;
    */
    //@Query("SELECT loan from Loan loan WHERE loan.user.id = ?1")
    List<Loan> findLoansByUserId (Long userId);
}
