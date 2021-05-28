package com.neighborhood.msneighborhood.service.impl;

import com.neighborhood.msneighborhood.entities.ServiceRequest;
import com.neighborhood.msneighborhood.repository.ServiceRequestRepository;
import com.neighborhood.msneighborhood.service.ServiceRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRequestServiceImpl.class);

    @Autowired
    ServiceRequestRepository serviceRequestRepository;

    @Override
    public List<ServiceRequest> findServiceRequestsByUserId(Long userId) {
        List<ServiceRequest> serviceRequestList = serviceRequestRepository.findServiceRequestsByUserId(userId);
        LOGGER.info("Envoie d'une liste de {} services (utilisateur id = {}).", serviceRequestList.size(), userId);
        return serviceRequestList;
    }
}
