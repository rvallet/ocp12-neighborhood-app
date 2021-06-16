package com.neighborhood.msneighborhood.service;

import com.neighborhood.msneighborhood.entities.GroupBuying;
import com.neighborhood.msneighborhood.entities.User;

import java.util.List;

public interface GroupBuyingService {

    GroupBuying findGroupBuyingsById (Long groupBuyingId);

    List<GroupBuying> getGroupBuyingsList();

    GroupBuying closeGroupBuying(Long groupBuyingId);

    GroupBuying updateGroupBuying (Long groupBuyingId, User user);

}
