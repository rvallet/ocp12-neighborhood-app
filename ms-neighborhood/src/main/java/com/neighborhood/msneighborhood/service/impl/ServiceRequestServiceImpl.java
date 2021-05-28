package com.neighborhood.msneighborhood.service.impl;

import com.neighborhood.msneighborhood.entities.ServiceRequest;
import com.neighborhood.msneighborhood.enumerated.RequestStatusEnum;
import com.neighborhood.msneighborhood.enumerated.ServiceRequestTypeEnum;
import com.neighborhood.msneighborhood.repository.ServiceRequestRepository;
import com.neighborhood.msneighborhood.service.ServiceRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public ServiceRequest save(ServiceRequest serviceRequestToCreate) {
        LOGGER.info(
                "Enregistrement d'une demande de service (user {} - type {})",
                serviceRequestToCreate.getUser().getEmail(),
                serviceRequestToCreate.getRequestType());
        return serviceRequestRepository.save(serviceRequestToCreate);
    }

    @Override
    public List<String> getServiceRequestTypeList() {
        List<String> result = new ArrayList<>();
        for (ServiceRequestTypeEnum type : ServiceRequestTypeEnum.values()) {
            result.add(type.toString());
        }
        LOGGER.info("Envoi d'une liste de {} types de services", result.size());
        return result;
    }

    @Override
    public List<ServiceRequest> findServiceRequestListByNeighborGroupId(Long groupId) {
        return serviceRequestRepository.findServiceRequestsByNeighborGroupIdAndFilteredByStatusList(groupId, getActiveServiceStatusList());
    }

    @Override
    public List<String> getActiveServiceStatusList() {
        return Arrays.asList(
                RequestStatusEnum.IN_PROGRESS.toString()
        );
    }

}
