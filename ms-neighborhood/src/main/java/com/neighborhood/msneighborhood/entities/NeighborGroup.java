package com.neighborhood.msneighborhood.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name="neighborgroup")
public class NeighborGroup implements Serializable {

    @Id
    @Column(name="id_neighborgroup")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "neighborGroup", fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<User> neighbors;

    public NeighborGroup() {
        super();
    }

    public NeighborGroup(String name) {
        this.name = name;
    }

    public NeighborGroup(String name, Collection<User> neighbors) {
        this.name = name;
        this.neighbors = neighbors;
    }

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

    public Collection<User> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Collection<User> neighbors) {
        this.neighbors = neighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NeighborGroup that = (NeighborGroup) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(neighbors, that.neighbors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, neighbors);
    }


}
