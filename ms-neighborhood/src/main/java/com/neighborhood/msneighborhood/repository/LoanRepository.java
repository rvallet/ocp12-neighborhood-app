package com.neighborhood.msneighborhood.repository;

import com.neighborhood.msneighborhood.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, String> {

    List<Loan> findAll();
    Loan findLoanById (Long loanId);

    List<Loan> findLoansByUserId (Long userId);
}
