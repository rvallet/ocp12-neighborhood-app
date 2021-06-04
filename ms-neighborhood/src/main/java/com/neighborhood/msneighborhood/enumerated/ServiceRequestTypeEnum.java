package com.neighborhood.msneighborhood.enumerated;

public enum ServiceRequestTypeEnum {

    TOOL_LOAN ("Emprunt d'outils"),
    GROUPED_PURCHASE ("Achat groupé"),
    MISCELLANEOUS_SERVICE ("Service divers");

    private String serviceRequestType;

    ServiceRequestTypeEnum(String serviceRequestType) {
        this.serviceRequestType = serviceRequestType;
    }

    @Override
    public String toString() {
        return serviceRequestType;
    }
}
