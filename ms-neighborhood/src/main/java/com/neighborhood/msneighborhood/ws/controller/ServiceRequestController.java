package com.neighborhood.msneighborhood.ws.controller;

import com.neighborhood.msneighborhood.api.ApiRegistration;
import com.neighborhood.msneighborhood.entities.Loan;
import com.neighborhood.msneighborhood.entities.ServiceRequest;
import com.neighborhood.msneighborhood.entities.User;
import com.neighborhood.msneighborhood.service.ServiceRequestService;
import com.neighborhood.msneighborhood.service.UserService;
import com.neighborhood.msneighborhood.ws.exception.NoSuchResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceRequestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRequestController.class);

    @Autowired
    ServiceRequestService requestService;

    @Autowired
    UserService userService;

    @GetMapping(value= ApiRegistration.REST_REQUEST_SERVICE_LIST_BY_USER_ID + "/{userId}")
    public List<ServiceRequest> findServiceRequestListByUserId(@PathVariable String userId) throws NoSuchResultException {
        LOGGER.debug("findRequestServiceListByUserId for userId = {}", userId);
        List<ServiceRequest> serviceList = requestService.findServiceRequestsByUserId(Long.parseLong(userId));
        LOGGER.info("Envoi d'une liste de {} demandes de services", serviceList.size());
        return serviceList;
    }

    @PostMapping(value = ApiRegistration.REST_CREATE_SERVICE_REQUEST + "/{userId}")
    public ServiceRequest createServiceRequest(@RequestBody ServiceRequest serviceRequest, @PathVariable Long userId) {
        ServiceRequest serviceRequestToCreate = new ServiceRequest();
        User user = userService.findUserById(userId);
        if (user!=null) {
            serviceRequestToCreate.setUser(user);
            serviceRequestToCreate.setRequestType(serviceRequest.getRequestType());
            serviceRequestToCreate.setDescription(serviceRequest.getDescription());
        } else {
            LOGGER.warn("Echec lors de la récupération de l'utilisateur id : {}", userId);
        }
        return requestService.save(serviceRequestToCreate);
    }

    @GetMapping(value = ApiRegistration.REST_GET_SERVICE_REQUEST_TYPE)
    public List<String> getServiceRequestTypeList() {
        return requestService.getServiceRequestTypeList();
    }
}
