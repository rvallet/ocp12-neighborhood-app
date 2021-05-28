package com.neighborhood.msneighborhood.enumerated;

public enum RequestStatusEnum {
    IN_PROGRESS ("En cours"),
    CLOSED ("Terminé");

    private String requestStatus;

    RequestStatusEnum(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public String toString() {
        return requestStatus;
    }
}
