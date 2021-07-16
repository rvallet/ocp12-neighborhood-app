package com.neighborhood.msneighborhood.service.impl;

import com.neighborhood.msneighborhood.entities.Loan;
import com.neighborhood.msneighborhood.enumerated.LoanStatusEnum;
import com.neighborhood.msneighborhood.repository.LoanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;

import static com.neighborhood.msneighborhood.mock.LoanMock.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
public class LoanServiceImplTest {

    @Mock
    LoanRepository loanRepository;

    @InjectMocks
    LoanServiceImpl loanService;

    @Test
    void findLoansByUserId(){
        List<Loan> loanList = getLoanMockList();

        given(loanRepository.findLoansByUserId(anyLong())).willReturn(loanList);

        Assertions.assertEquals(
                loanList.size(),
                loanService.findLoansByUserId(1L).size(),
                "Liste des emprunts par userId"
        );
    }

    @Test
    void getLoansList(){
        List<Loan> loanList = getLoanMockList();

        given(loanRepository.findAll()).willReturn(loanList);

        Assertions.assertEquals(
                loanList.size(),
                loanService.findLoansByUserId(1L).size(),
                "Liste de tous les emprunts"
        );

    }

    @Test
    @Transactional
    void closeLoan(){
        Loan loan = getLoanMock();
        loan.setId(0L);

        given(loanRepository.findLoanById(anyLong())).willReturn(loan);
        given(loanRepository.save(Mockito.any(Loan.class))).willReturn(loan);

        Loan updatedLoan = loanService.closeLoan(loan.getId());

        Assertions.assertEquals(
                LoanStatusEnum.CLOSED.toString(),
                updatedLoan.getLoanStatus(),
                "Statut fermé"
        );

    }
}
