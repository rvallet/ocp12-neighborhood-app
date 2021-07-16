package com.neighborhood.msneighborhood.service.impl;

import com.neighborhood.msneighborhood.entities.NeighborGroup;
import com.neighborhood.msneighborhood.repository.NeighborGroupRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.neighborhood.msneighborhood.mock.NeighborGroupServiceMock.getNeighborGroupMock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
public class NeighborGroupServiceImplTest {

    @Mock
    NeighborGroupRepository neighborGroupRepository;

    @InjectMocks
    NeighborGroupServiceImpl neighborGroupService;

    @Test
    void save() {
        NeighborGroup neighborGroup = getNeighborGroupMock();

        given(neighborGroupRepository.save(any(NeighborGroup.class))).willReturn(neighborGroup);

        Assertions.assertEquals(
                neighborGroup.getName(),
                neighborGroupService.save(neighborGroup).getName(),
                "Enregistrement d'un groupe de voisinage"
        );

    }

}
