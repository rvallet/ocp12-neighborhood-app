package com.neighborhood.msneighborhood.service.impl;

import com.neighborhood.msneighborhood.entities.Loan;
import com.neighborhood.msneighborhood.entities.ServiceRequest;
import com.neighborhood.msneighborhood.entities.User;
import com.neighborhood.msneighborhood.enumerated.RequestStatusEnum;
import com.neighborhood.msneighborhood.enumerated.ServiceRequestTypeEnum;
import com.neighborhood.msneighborhood.repository.LoanRepository;
import com.neighborhood.msneighborhood.repository.ServiceRequestRepository;
import com.neighborhood.msneighborhood.repository.UserRepository;
import com.neighborhood.msneighborhood.service.ServiceRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRequestServiceImpl.class);

    @Autowired
    ServiceRequestRepository serviceRequestRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoanRepository loanRepository;

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
        List<ServiceRequest> serviceRequestList = serviceRequestRepository.findServiceRequestsByNeighborGroupIdAndFilteredByStatusList(groupId, getActiveServiceStatusList());
        LOGGER.info(
                "Envoi d'une liste de {} services pour le groupeId : {}",
                serviceRequestList.size(),
                groupId);
        return serviceRequestList;
    }

    @Override
    public List<String> getActiveServiceStatusList() {
        return Arrays.asList(
                RequestStatusEnum.IN_PROGRESS.toString()
        );
    }

    @Override
    public ServiceRequest processServiceResponse(Long serviceId, Long userId) {
        ServiceRequest serviceRequest = serviceRequestRepository.findServiceRequestById(serviceId);
        User helperUser = userRepository.findUserById(userId);

        if (serviceRequest != null && helperUser != null) {

            if (serviceRequest.getRequestType().equals(ServiceRequestTypeEnum.TOOL_LOAN.toString())) {
                User helpedUser = userRepository.findUserById(serviceRequest.getUser().getId());
                Loan loan = new Loan(helpedUser);
                loan.setTitle(serviceRequest.getDescription());
                loan.setOwnerId(helperUser.getId());
                loan.setOwnerFullName(helperUser.getFullName());
                loan.setUserId(serviceRequest.getOwnerId());
                LOGGER.info(
                        "Création d'un prêt (Emprunteur : {} - Propriétaire : {})",
                        helpedUser.getEmail(),
                        helperUser.getEmail()
                );
                loanRepository.save(loan);
            }

            serviceRequest.setRequestStatus(RequestStatusEnum.CLOSED.toString());
            serviceRequest.setHelper(helperUser.getFullName());
            serviceRequest.setClosingDate(new Date());
        }

        LOGGER.info(
                "Clotûre de le demande de serviceId {}",
                serviceRequest.getId()
                );
        ServiceRequest updatedServiceRequest = serviceRequestRepository.save(serviceRequest);
        return updatedServiceRequest;
    }

}
