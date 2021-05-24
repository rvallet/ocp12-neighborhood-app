package com.neighborhood.msneighborhood.ws.controller;

import com.neighborhood.msneighborhood.api.ApiRegistration;
import com.neighborhood.msneighborhood.entities.Loan;
import com.neighborhood.msneighborhood.service.LoanService;
import com.neighborhood.msneighborhood.ws.exception.NoSuchResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    LoanService loanService;

    @GetMapping(value= ApiRegistration.REST_LOANS_LIST_BY_USER_ID + "/{userId}")
    public List<Loan> findLoansListByUserId(@PathVariable String userId) throws NoSuchResultException {
        LOGGER.debug("findLoansListByUserId for userId = {}", userId);
        List<Loan> loanList = loanService.findLoansByUserId(Long.parseLong(userId));
        LOGGER.info("Envoi d'une liste de {} emprunts", loanList.size());
        return loanList;
    }

}
