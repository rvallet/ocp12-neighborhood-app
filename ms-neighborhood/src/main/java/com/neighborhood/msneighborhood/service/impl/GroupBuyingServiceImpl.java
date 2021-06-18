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
import java.util.stream.Collectors;

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
        result.stream()
                .filter(e -> GroupBuyingStatusEnum.IN_PROGRESS.toString().equalsIgnoreCase(e.getGroupBuyingStatus()))
                .collect(Collectors.toList());
        LOGGER.info("getGroupBuyingsList (Statut 'En Cours'): {}", result.size());
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

        if (groupBuying != null && !groupBuying.getUserList().stream().anyMatch(u -> u.getId() == user.getId())) {
            groupBuying.getUserList().add(user);
            groupBuyingRepository.save(groupBuying);
            LOGGER.info(
                    "updateGroupBuying  : id = {} - status = {} - participants = {}",
                    groupBuying.getId(),
                    groupBuying.getGroupBuyingStatus(),
                    groupBuying.getUserList().size()
            );
        } else {
            LOGGER.warn(
                    "L'utilisateur participe déjà à cet achat groupé (userId {} - groupBuyingId {}",
                    user.getId(),
                    groupBuyingId);
        }

        return groupBuying;
    }
}
