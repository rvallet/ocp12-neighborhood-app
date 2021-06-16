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

    @GetMapping(value= ApiRegistration.REST_GET_LOANS_LIST_BY_USER_ID + "/{userId}")
    public List<Loan> findLoansListByUserId(@PathVariable String userId) throws NoSuchResultException {
        LOGGER.debug("findLoansListByUserId for userId = {}", userId);
        List<Loan> loanList = loanService.findLoansByUserId(Long.parseLong(userId));
        LOGGER.info("Envoi d'une liste de {} emprunts", loanList.size());
        return loanList;
    }

    @GetMapping(value= ApiRegistration.REST_GET_LOANS_LIST)
    public List<Loan> getLoansList() throws NoSuchResultException {
        LOGGER.debug("getLoansList()");
        List<Loan> loansList = loanService.getLoansList();
        LOGGER.info("Envoi d'une liste de {} emprunts", loansList.size());
        return loansList;
    }

    @GetMapping(value = ApiRegistration.REST_GET_CLOSE_LOAN + "/{loanId}")
    public Loan closeLoan(@PathVariable Long loanId) {
        LOGGER.info("Reception d'une demande de clotûre de l'emprunt id {}", loanId);
        return loanService.closeLoan(loanId);
    }

}
