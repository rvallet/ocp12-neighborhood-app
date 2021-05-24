package com.neighborhood.msneighborhood.service;

import com.neighborhood.msneighborhood.entities.Loan;

import java.util.List;

public interface LoanService {

    List<Loan> findLoansByUserId (Long userId);

}
