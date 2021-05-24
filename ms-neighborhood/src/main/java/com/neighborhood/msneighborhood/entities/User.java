package com.neighborhood.msneighborhood.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements Serializable {

    @Id
    @Column(name="id_user")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String lastName;

    private String firstName;

    private String email;

    private String password;

    private String role;

    private String resetToken;

    private Date creationDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_address",
            joinColumns =
                    { @JoinColumn(name = "id_user", referencedColumnName = "id_user") },
            inverseJoinColumns =
                    { @JoinColumn(name = "id_address", referencedColumnName = "id_address") })
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_neighborgroup")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private NeighborGroup neighborGroup;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Collection<Loan> loan;

    public User() {
        super();
        this.creationDate= Calendar.getInstance().getTime();
    }

    public User(String email, String lastName, String firstName, String password, String role) {
        super();
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
        this.role = role;
        this.creationDate = Calendar.getInstance().getTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public NeighborGroup getNeighborGroup() {
        return neighborGroup;
    }

    public void setNeighborGroup(NeighborGroup neighborGroup) {
        this.neighborGroup = neighborGroup;
    }

    public Collection<Loan> getLoan() {
        return loan;
    }

    public void setLoan(Collection<Loan> loan) {
        this.loan = loan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(role, user.role) && Objects.equals(resetToken, user.resetToken) && Objects.equals(creationDate, user.creationDate) && Objects.equals(address, user.address) && Objects.equals(neighborGroup, user.neighborGroup) && Objects.equals(loan, user.loan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, email, password, role, resetToken, creationDate, address, neighborGroup, loan);
    }

}
