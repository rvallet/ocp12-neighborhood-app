package com.neighborhood.msneighborhood.mock;

import com.neighborhood.msneighborhood.entities.GroupBuying;
import com.neighborhood.msneighborhood.enumerated.GroupBuyingStatusEnum;

import java.util.Arrays;
import java.util.List;

public class GroupBuyingMock {

    public static GroupBuying getGroupBuyingMock(){
        GroupBuying result = new GroupBuying();
        result.setId(0l);
        result.setTitle("Title");
        result.setOwnerFullName("OwnerFullName");
        result.setOwnerId(0L);
        result.setNeighborGroupId(0L);
        return result;
    }

    public static List<GroupBuying> getGroupBuyingListMock(){
        return Arrays.asList(getGroupBuyingMock(), getGroupBuyingMock());
    }

    public static List<String> getCurrentGroupBuyingStatusEnumListMock() {
        return Arrays.asList(
                GroupBuyingStatusEnum.IN_PROGRESS.toString(),
                GroupBuyingStatusEnum.CLOSED.toString()
        );
    }

}
