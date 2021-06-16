package com.neighborhood.msneighborhood.enumerated;

public enum GroupBuyingStatusEnum {

    IN_PROGRESS ("En cours"),
    CLOSED ("Terminé");

    private String groupBuyingStatus;

    GroupBuyingStatusEnum(String groupBuyingStatus) {
        this.groupBuyingStatus = groupBuyingStatus;
    }

    @Override
    public String toString() {
        return groupBuyingStatus;
    }

}
