package com.neighborhood.msneighborhood.service.impl;

import com.neighborhood.msneighborhood.entities.GroupBuying;
import com.neighborhood.msneighborhood.entities.User;
import com.neighborhood.msneighborhood.enumerated.GroupBuyingStatusEnum;
import com.neighborhood.msneighborhood.repository.GroupBuyingRepository;
import com.neighborhood.msneighborhood.service.GroupBuyingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GroupBuyingServiceImpl implements GroupBuyingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupBuyingServiceImpl.class);

    @Autowired
    GroupBuyingRepository groupBuyingRepository;


    @Override
    public GroupBuying findGroupBuyingsById(Long groupBuyingId) {
        LOGGER.info("findGroupBuyingsById : {}", groupBuyingId);
        return groupBuyingRepository.findGroupBuyingById(groupBuyingId);
    }

    @Override
    public List<GroupBuying> getGroupBuyingsList() {
        List<GroupBuying> result = groupBuyingRepository.findAll();
        LOGGER.info("getGroupBuyingsList : {}", result.size());
        return result;
    }

    @Override
    public GroupBuying closeGroupBuying(Long groupBuyingId) {
        GroupBuying groupBuying = groupBuyingRepository.findGroupBuyingById(groupBuyingId);
        groupBuying.setPurchaseDate(new Date());
        groupBuying.setGroupBuyingStatus(GroupBuyingStatusEnum.CLOSED.toString());
        LOGGER.info(
                "closeGroupBuying : id = {} - status = {} - purchaseDate = {}",
                groupBuying.getId(),
                groupBuying.getGroupBuyingStatus(),
                groupBuying.getPurchaseDate()
        );
        return groupBuyingRepository.save(groupBuying);

    }

    @Override
    public GroupBuying updateGroupBuying(Long groupBuyingId, User user) {
        GroupBuying groupBuying = groupBuyingRepository.findGroupBuyingById(groupBuyingId);

        groupBuying.getUserList().add(user);

        LOGGER.info(
                "updateGroupBuying  : id = {} - status = {} - participants = {}",
                groupBuying.getId(),
                groupBuying.getGroupBuyingStatus(),
                groupBuying.getUserList().size()
        );
        return groupBuyingRepository.save(groupBuying);
    }
}
