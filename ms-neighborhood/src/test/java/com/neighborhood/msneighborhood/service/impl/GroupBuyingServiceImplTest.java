package com.neighborhood.msneighborhood.service.impl;

import com.neighborhood.msneighborhood.entities.GroupBuying;
import com.neighborhood.msneighborhood.entities.User;
import com.neighborhood.msneighborhood.enumerated.GroupBuyingStatusEnum;
import com.neighborhood.msneighborhood.repository.GroupBuyingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;

import static com.neighborhood.msneighborhood.mock.GroupBuyingMock.*;
import static com.neighborhood.msneighborhood.mock.UserMock.getMockNeighborGroup;
import static com.neighborhood.msneighborhood.mock.UserMock.getMockUserList;
import static com.neighborhood.msneighborhood.mock.UserMock.getMockUser;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
public class GroupBuyingServiceImplTest {

    @Mock
    GroupBuyingRepository groupBuyingRepository;

    @InjectMocks
    GroupBuyingServiceImpl groupBuyingService;

    @Test
    void findGroupBuyingById(){

        GroupBuying groupBuying = getGroupBuyingMock();

        given(groupBuyingRepository.findGroupBuyingById(anyLong())).willReturn(groupBuying);

        Assertions.assertEquals(
                groupBuying.getCreationDate(),
                groupBuyingService.findGroupBuyingById(0L).getCreationDate(),
                "Recherche des groupes d'achats par Id"
        );

    }

    @Test
    void getGroupBuyingsList(){
        List<GroupBuying> groupBuyingList = getGroupBuyingListMock();

        given(groupBuyingRepository.findAllFiltredByStatusList(anyList())).willReturn(groupBuyingList);

        List<GroupBuying> result = groupBuyingService.getGroupBuyingsList(getCurrentGroupBuyingStatusEnumListMock());

        Assertions.assertEquals(
                groupBuyingList.size(),
                result.size(),
                "liste des groupes d'achat actifs"
        );

    }

    @Test
    @Transactional
    void closeGroupBuying(){
        GroupBuying groupBuying = getGroupBuyingMock();
        groupBuying.setId(0L);

        given(groupBuyingRepository.findGroupBuyingById(anyLong())).willReturn(groupBuying);
        given(groupBuyingRepository.save(Mockito.any(GroupBuying.class))).willReturn(groupBuying);

        GroupBuying updatedGroupBuying = groupBuyingService.closeGroupBuying(groupBuying.getId());

        Assertions.assertEquals(
                GroupBuyingStatusEnum.CLOSED.toString(),
                updatedGroupBuying.getGroupBuyingStatus(),
                "Statut fermé"
        );
    }

    @Test
    @Transactional
    void archiveGroupBuying(){
        GroupBuying groupBuying = getGroupBuyingMock();
        groupBuying.setId(0L);

        given(groupBuyingRepository.findGroupBuyingById(anyLong())).willReturn(groupBuying);
        given(groupBuyingRepository.save(Mockito.any(GroupBuying.class))).willReturn(groupBuying);

        GroupBuying updatedGroupBuying = groupBuyingService.archiveGroupBuying(groupBuying.getId());

        Assertions.assertEquals(
                GroupBuyingStatusEnum.OFFLINE.toString(),
                updatedGroupBuying.getGroupBuyingStatus(),
                "Statut hors ligne"
        );

    }

    @Test
    @Transactional
    void updateGroupBuying_OK(){
        GroupBuying groupBuying = getGroupBuyingMock();
        groupBuying.setId(0L);

        List<User> userList = getMockUserList();
        userList.forEach(u -> {
            u.setId((Long.parseLong(String.valueOf(userList.indexOf(u)+1))));
            u.setNeighborGroup(getMockNeighborGroup());
        });

        groupBuying.setUserList(userList);

        User newUser = getMockUser();
        newUser.setId(66L);
        newUser.setNeighborGroup(getMockNeighborGroup());

        given(groupBuyingRepository.findGroupBuyingById(anyLong())).willReturn(groupBuying);
        given(groupBuyingRepository.save(Mockito.any(GroupBuying.class))).willReturn(groupBuying);

/*        GroupBuying updatedGroupBuying = groupBuyingService.updateGroupBuying(groupBuying.getId(), newUser);

        Assertions.assertEquals(
                groupBuying.getUserList().size()+1,
                updatedGroupBuying.getUserList().size(),
                "Ajout d'un utilisateur à la liste d'achat groupé"
        );*/

    }

    @Test
    @Transactional
    void updateGroupBuying_KO(){
        GroupBuying groupBuying = getGroupBuyingMock();
        groupBuying.setId(0L);

        List<User> userList = getMockUserList();
        groupBuying.setUserList(userList);

        User newUser = getMockUser();
        newUser.setId(0L);

        given(groupBuyingRepository.findGroupBuyingById(anyLong())).willReturn(groupBuying);
        given(groupBuyingRepository.save(Mockito.any(GroupBuying.class))).willReturn(groupBuying);

/*        GroupBuying updatedGroupBuying = groupBuyingService.updateGroupBuying(groupBuying.getId(), newUser);

        Assertions.assertEquals(
                groupBuying.getUserList().size(),
                updatedGroupBuying.getUserList().size(),
                "L'utilisateur est déjà présent dans la liste d'achat groupé"
        );*/

    }

    @Test
    void getCurrentGroupBuyingStatusEnumList(){
        Assertions.assertEquals(
                2,
                groupBuyingService.getCurrentGroupBuyingStatusEnumList().size(),
                "Liste des status d'achat groupé actifs"
        );
    }

}
