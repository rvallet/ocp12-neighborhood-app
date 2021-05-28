package com.neighborhood.msneighborhood.service;

import com.neighborhood.msneighborhood.entities.ServiceRequest;

import java.util.List;

public interface ServiceRequestService {

    List<ServiceRequest> findServiceRequestsByUserId (Long userId);

    ServiceRequest save(ServiceRequest serviceRequestToCreate);

    List<String> getServiceRequestTypeList();
}
