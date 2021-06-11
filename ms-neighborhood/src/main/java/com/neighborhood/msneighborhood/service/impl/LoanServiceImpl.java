package com.neighborhood.msneighborhood.service.impl;

import com.neighborhood.msneighborhood.entities.Loan;
import com.neighborhood.msneighborhood.enumerated.LoanStatusEnum;
import com.neighborhood.msneighborhood.repository.LoanRepository;
import com.neighborhood.msneighborhood.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanServiceImpl.class);

    @Autowired
    LoanRepository loanRepository;

    @Override
    public List<Loan> findLoansByUserId(Long userId) {
        List<Loan> loanList = loanRepository.findLoansByUserId(userId);
        LOGGER.info("Envoie d'une liste de {} emprunts (utilisateur id = {}).", loanList.size(), userId);
        return loanList;
    }

    @Override
    public Loan closeLoan(Long loanId) {
        Loan loan = loanRepository.findLoanById(loanId);
        loan.setReturnLoan(new Date());
        loan.setLoanStatus(LoanStatusEnum.CLOSED.toString());
        return loanRepository.save(loan);
    }

}
