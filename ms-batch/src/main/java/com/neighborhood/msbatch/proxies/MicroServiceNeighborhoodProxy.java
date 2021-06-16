package com.neighborhood.msbatch.proxies;


import com.neighborhood.msbatch.beans.LoanBean;
import com.neighborhood.msbatch.beans.UserBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-neighborhood")
@RibbonClient(name = "ms-neighborhood")
public interface MicroServiceNeighborhoodProxy {

    /* Loans */
    @GetMapping(value= "/getLoansList")
    List<LoanBean> getLoansList();

    @GetMapping(value= "/findLoansListByUserId/{userId}")
    List<LoanBean> getLoansByUserId(@PathVariable Long userId);

    @GetMapping(value= "/closeLoan/{loanId}")
    LoanBean closeLoan(@PathVariable Long loanId);

    /* User */
    @GetMapping(value = "/getUserById/{userId}")
    UserBean getUserById(@PathVariable Long userId);

}
