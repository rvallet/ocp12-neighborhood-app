package com.neighborhood.msneighborhood.service.impl;

import com.neighborhood.msneighborhood.entities.NeighborGroup;
import com.neighborhood.msneighborhood.repository.NeighborGroupRepository;
import com.neighborhood.msneighborhood.service.NeighborGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NeighborGroupServiceImpl implements NeighborGroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NeighborGroupServiceImpl.class);

    @Autowired
    NeighborGroupRepository neighborGroupRepository;

    @Override
    public NeighborGroup save(NeighborGroup neighborGroup) {
        return neighborGroupRepository.save(neighborGroup);
    }

}
