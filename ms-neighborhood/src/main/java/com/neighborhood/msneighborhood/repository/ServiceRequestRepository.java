package com.neighborhood.msneighborhood.repository;

import com.neighborhood.msneighborhood.entities.ServiceRequest;
import com.neighborhood.msneighborhood.enumerated.RequestStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, String> {

    List<ServiceRequest> findAll();
    ServiceRequest findServiceRequestById (Long serviceRequestId);
    List<ServiceRequest> findServiceRequestsByUserId(Long userId);

    @Query("SELECT sr FROM ServiceRequest sr WHERE sr.user.neighborGroup.id = ?1 AND sr.requestStatus IN ?2 ORDER BY sr.creationDate ASC")
    List<ServiceRequest> findServiceRequestsByNeighborGroupIdAndFilteredByStatusList(Long groupId, List<String> requestStatus);
}
