package com.neighborhood.msneighborhood.service;

import com.neighborhood.msneighborhood.entities.GroupBuying;
import com.neighborhood.msneighborhood.entities.User;

import java.util.List;

public interface GroupBuyingService {

    GroupBuying findGroupBuyingById (Long groupBuyingId);

    List<GroupBuying> getGroupBuyingsList(List<String> groupBuyingStatusEnum);

    GroupBuying closeGroupBuying(Long groupBuyingId);

    GroupBuying archiveGroupBuying(Long groupBuyingId);

    GroupBuying updateGroupBuying (Long groupBuyingId, User user);

    List<String> getCurrentGroupBuyingStatusEnumList();

}
