package com.neighborhood.msneighborhood.service.impl;

import com.neighborhood.msneighborhood.entities.ServiceRequest;
import com.neighborhood.msneighborhood.enumerated.ServiceRequestTypeEnum;
import com.neighborhood.msneighborhood.repository.ServiceRequestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.neighborhood.msneighborhood.mock.ServiceRequestMock.*;
import static com.neighborhood.msneighborhood.mock.UserMock.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
public class ServiceRequestServiceImplTest {

    @Mock
    ServiceRequestRepository serviceRequestRepository;

    @InjectMocks
    ServiceRequestServiceImpl serviceRequestService;

    @Test
    void findServiceRequestsByUserId() {
        List<ServiceRequest> serviceRequestList = getServiceRequestListMock();

        given(serviceRequestRepository.findServiceRequestsByUserId(anyLong())).willReturn(serviceRequestList);

        Assertions.assertEquals(
                2,
                serviceRequestService.findServiceRequestsByUserId(0L).size(),
                "Recherche des demandes de services utilisateur par userId"
        );
    }

    @Test
    void save() {
        ServiceRequest serviceRequest = getServiceRequestMock(ServiceRequestTypeEnum.MISCELLANEOUS_SERVICE);
        serviceRequest.setUser(getMockUser());

        given(serviceRequestRepository.save(any(ServiceRequest.class))).willReturn(serviceRequest);

        Assertions.assertEquals(
                serviceRequest.getUser().getId(),
                serviceRequestService.save(serviceRequest).getUser().getId(),
                "Enregistrement d'une demande utilisateur"
        );

    }

    @Test
    void getServiceRequestTypeList(){
        Assertions.assertEquals(
                ServiceRequestTypeEnum.values().length,
                serviceRequestService.getServiceRequestTypeList().size(),
                "Liste des type de demandes"
        );
    }

    @Test
    void findServiceRequestListByNeighborGroupId(){
        List<ServiceRequest> serviceRequestList = getServiceRequestListMock();

        given(serviceRequestRepository.findServiceRequestsByNeighborGroupIdAndFilteredByStatusList(anyLong(), anyList())).willReturn(serviceRequestList);

        Assertions.assertEquals(
                2,
                serviceRequestService.findServiceRequestListByNeighborGroupId(0L).size(),
                "Recherche des demandes de services utilisateur par neighborGroupId"
        );

    }

    @Test
    void getActiveServiceStatusList(){
        Assertions.assertEquals(
                1,
                serviceRequestService.getActiveServiceStatusList().size(),
                "Liste des status de service actifs"
        );
    }


}
