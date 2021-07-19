package com.neighborhood.msneighborhood.mock;

import com.neighborhood.msneighborhood.entities.ServiceRequest;
import com.neighborhood.msneighborhood.entities.User;
import com.neighborhood.msneighborhood.enumerated.ServiceRequestTypeEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceRequestMock {

    public static ServiceRequest getServiceRequestMock(){
        ServiceRequest serviceRequest = new ServiceRequest();
        return serviceRequest;
    }

    public static ServiceRequest getServiceRequestMock(ServiceRequestTypeEnum serviceRequestType){
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setRequestType(serviceRequestType.toString());
        return serviceRequest;
    }

    public static ServiceRequest getServiceRequestMock(User user){
        ServiceRequest serviceRequest = new ServiceRequest(user);
        return serviceRequest;
    }

    public static List<ServiceRequest> getServiceRequestListMock(){
        return Arrays.asList(getServiceRequestMock(), getServiceRequestMock());
    }

    public static List<ServiceRequest> getServiceRequestListMock(int size){
        List<ServiceRequest> result = new ArrayList<>();
        for (int i=1; i<size+1; i++) {
            result.add(getServiceRequestMock());
        }
        return result;
    }

}
