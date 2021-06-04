package com.neighborhood.website.proxies;

import com.neighborhood.website.beans.LoanBean;
import com.neighborhood.website.beans.ServiceRequestBean;
import com.neighborhood.website.beans.UserBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

@FeignClient(name = "ms-neighborhood")
@RibbonClient(name = "ms-neighborhood")
public interface MicroServiceNeighborhoodProxy {

    /* Users */
    @GetMapping(value= "/users")
    List<UserBean> getUsers();

    @GetMapping(value= "/users/page/{pageNumber}/{pageSize}")
    Page<UserBean> getPaginatedUsers(@PathVariable int pageNumber, @PathVariable int pageSize);

    @GetMapping(value = "/userDetails")
    UserDetails getUserDetails();

    @GetMapping(value = "/getUserByEmail/{email}")
    UserBean getUserByEmail(@PathVariable String email);

    @PostMapping(value = "/saveUser")
    UserBean saveUser(@RequestBody UserBean user);

    @GetMapping(value="/getRoleList")
    List<String> getRoleList();

    /* Neighbors */
    @GetMapping(value= "/neighbors/{groupId}")
    List<UserBean> getNeighborsByGroupId(@PathVariable Long groupId);

    /* Services */
    @PostMapping(value = "/createServiceRequest/{userId}")
    ServiceRequestBean createServiceRequest(@RequestBody ServiceRequestBean serviceRequest, @PathVariable Long userId);

    @GetMapping(value = "getServiceRequestTypeList")
    List<String> getServiceRequestTypeList();

    @GetMapping(value = "getServiceRequestListByNeighborgroupId/{groupId}")
    List<ServiceRequestBean> getServiceRequestListByNeighborgroupId(@PathVariable Long groupId);

    /* Loans */
    @GetMapping(value= "/findLoansListByUserId/{userId}")
    List<LoanBean> getLoansByUserId(@PathVariable Long userId);

}
