package com.neighborhood.msbatch.beans;

import java.util.List;

public class NeighborGroupBean {

    private Long id;

    private String name;

    private List<UserBean> neighbors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserBean> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<UserBean> neighbors) {
        this.neighbors = neighbors;
    }
}
