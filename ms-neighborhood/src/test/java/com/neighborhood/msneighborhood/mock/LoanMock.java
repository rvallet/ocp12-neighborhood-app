package com.neighborhood.msneighborhood.mock;

import com.neighborhood.msneighborhood.entities.Loan;

import java.util.Arrays;
import java.util.List;

public class LoanMock {

    public static Loan getLoanMock(){
        return new Loan();
    }

    public static List<Loan> getLoanMockList(){
        return Arrays.asList(getLoanMock(), getLoanMock());
    }
}
