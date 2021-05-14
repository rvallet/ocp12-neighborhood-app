package com.neighborhood.msneighborhood.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="adresse")
public class Adresse implements Serializable {

    @Id
    @Column(name="id_adresse")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String number;

    private String streetName;

    private String postCode;

    private String city;

    @OneToOne
    private User user;

    public Adresse(){
        super();
    }

    public Adresse(String number, String streetName, String postCode, String city) {
        this.number = number;
        this.streetName = streetName;
        this.postCode = postCode;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse = (Adresse) o;
        return Objects.equals(id, adresse.id) && Objects.equals(number, adresse.number) && Objects.equals(streetName, adresse.streetName) && Objects.equals(postCode, adresse.postCode) && Objects.equals(city, adresse.city) && Objects.equals(user, adresse.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, streetName, postCode, city, user);
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", streetName='" + streetName + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", user=" + user +
                '}';
    }
}
