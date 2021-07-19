package com.neighborhood.msneighborhood.mock;

import com.neighborhood.msneighborhood.entities.NeighborGroup;

public class NeighborGroupServiceMock {

    public static NeighborGroup getNeighborGroupMock(){
        return new NeighborGroup("neighborGroupName");
    }
}
