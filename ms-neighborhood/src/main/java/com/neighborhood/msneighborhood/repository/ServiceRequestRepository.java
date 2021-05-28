package com.neighborhood.msneighborhood.repository;

import com.neighborhood.msneighborhood.entities.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, String> {

    List<ServiceRequest> findAll();
    ServiceRequest findServiceRequestById (Long serviceRequestId);
    List<ServiceRequest> findServiceRequestsByUserId(Long userId);
}
