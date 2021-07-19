package com.neighborhood.msneighborhood.enumerated;

import java.util.Arrays;

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

    public static ServiceRequestTypeEnum of(String value) {
        return Arrays.stream(values()).filter(v -> v.toString().equalsIgnoreCase(value)).findFirst().orElse(null);
    }
}
