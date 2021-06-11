package com.neighborhood.msbatch.service.impl;

import com.neighborhood.msbatch.beans.LoanBean;
import com.neighborhood.msbatch.beans.UserBean;
import com.neighborhood.msbatch.entities.LoanEmailReminder;
import com.neighborhood.msbatch.proxies.MicroServiceNeighborhoodProxy;
import com.neighborhood.msbatch.repository.LoanEmailReminderRepository;
import com.neighborhood.msbatch.service.LoanEmailReminderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanEmailReminderServiceImpl implements LoanEmailReminderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanEmailReminderServiceImpl.class);

    @Autowired
    private MicroServiceNeighborhoodProxy microServiceNeighborhoodProxy;

    @Autowired
    LoanEmailReminderRepository loanEmailReminderRepository;

    @Override
    public List<LoanBean> getLoanList() {
        LOGGER.info("Récupération de la liste des emprunts");
        return microServiceNeighborhoodProxy.getLoansList();
    }

    @Override
    public void feedLoanEmailReminderRepository() {
        List<LoanEmailReminder> result = new ArrayList<>();
        List<LoanBean> loanBeanList = getLoanList();

        if (!CollectionUtils.isEmpty(loanBeanList)) {
            List<LoanBean> todayLoanList = loanBeanList.stream()
                    .filter(b -> !b.getLoanStatus().equalsIgnoreCase("Terminé"))
                    .filter(b -> checkIfLoanReminderShouldBeCreated(b))
                    .collect(Collectors.toList());

            LOGGER.info("Filtered loanList : {}", todayLoanList.size());

            // TODO : getUser here

            todayLoanList.forEach(e -> {
                UserBean user = microServiceNeighborhoodProxy.getUserById(e.getUserId());
                result.add(new LoanEmailReminder(
                        user.getId(),
                        user.getEmail(),
                        user.getLastName(),
                        user.getFirstName(),
                        e.getOwnerId(),
                        e.getId(),
                        e.getTitle(),
                        e.getEndLoan()
                ));
            });
            saveLoanEmailReminderList(result);
        }

    }

    private boolean checkIfLoanReminderShouldBeCreated(LoanBean loan) {
        List<LoanEmailReminder> loanEmailReminderList = loanEmailReminderRepository
                .findLoanEmailRemindersByLoanIdIs(loan.getId());
        return CollectionUtils.isEmpty(loanEmailReminderList);
    }

    @Override
    public void saveLoanEmailReminderList(List<LoanEmailReminder> loanEmailReminderList) {
        loanEmailReminderRepository.saveAll(loanEmailReminderList);
    }

    @Override
    public List<LoanEmailReminder> findLoanEmailRemindersByIsEmailSentIsNot(Boolean isEmailSent) {
        return loanEmailReminderRepository.findLoanEmailReminderByEmailSentIsNot(isEmailSent);
    }

    @Override
    public void saveLoanEmailReminder(LoanEmailReminder loanEmailReminder) {
        loanEmailReminderRepository.save(loanEmailReminder);
    }

}
