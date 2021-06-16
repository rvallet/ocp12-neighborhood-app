package com.neighborhood.msneighborhood.ws.controller;

import com.neighborhood.msneighborhood.api.ApiRegistration;
import com.neighborhood.msneighborhood.entities.GroupBuying;
import com.neighborhood.msneighborhood.entities.Loan;
import com.neighborhood.msneighborhood.entities.User;
import com.neighborhood.msneighborhood.service.GroupBuyingService;
import com.neighborhood.msneighborhood.service.UserService;
import com.neighborhood.msneighborhood.ws.exception.NoSuchResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupBuyingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupBuyingController.class);

    @Autowired
    GroupBuyingService groupBuyingService;

    @Autowired
    UserService userService;

    @GetMapping(value= ApiRegistration.REST_GET_GROUP_BUYINGS_LIST)
    public List<GroupBuying> getGroupBuyingsList() throws NoSuchResultException {
        LOGGER.debug("getGroupBuyingsList()");
        List<GroupBuying> groupBuyingsList = groupBuyingService.getGroupBuyingsList();
        LOGGER.info("Envoi d'une liste de {} achats groupés", groupBuyingsList.size());
        return groupBuyingsList;
    }

    @GetMapping(value = ApiRegistration.REST_GET_CLOSE_GROUP_BUYING + "/{groupBuyingId}")
    public GroupBuying closeGroupBuying(@PathVariable Long groupBuyingId) {
        LOGGER.info("Réception d'une demande de clôture de l'achat groupé id {}", groupBuyingId);
        return groupBuyingService.closeGroupBuying(groupBuyingId);
    }

    @GetMapping(value = ApiRegistration.REST_UPDATE_GROUP_BUYING + "/{groupBuyingId}" + "/{userId}")
    public GroupBuying updateGroupBuying(@PathVariable Long groupBuyingId, @PathVariable Long userId) {
        LOGGER.info("Réception d'une demande de mise à jour de l'achat groupé id {}", groupBuyingId);
        User user = userService.findUserById(userId);
        return groupBuyingService.updateGroupBuying(groupBuyingId, user);
    }

}
