package com.neighborhood.msneighborhood.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neighborhood.msneighborhood.enumerated.RequestStatusEnum;
import com.neighborhood.msneighborhood.enumerated.ServiceRequestTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="request")
public class ServiceRequest implements Serializable {

    @Id
    @Column(name="id_request")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String requestType;

    private String description;

    private String requestStatus;

    private String author;

    private String helper;

    private Date creationDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonBackReference
    private User user;

    public ServiceRequest(){
        super();
        this.creationDate = new Date();
        this.requestType = ServiceRequestTypeEnum.MISCELLANEOUS_SERVICE.toString();
        this.requestStatus = RequestStatusEnum.IN_PROGRESS.toString();
    }

    public ServiceRequest(User user){
        super();
        this.creationDate = new Date();
        this.requestType = ServiceRequestTypeEnum.MISCELLANEOUS_SERVICE.toString();
        this.requestStatus = RequestStatusEnum.IN_PROGRESS.toString();
        this.author = user.getFullName();
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHelper() {
        return helper;
    }

    public void setHelper(String helper) {
        this.helper = helper;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
        ServiceRequest that = (ServiceRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(requestType, that.requestType) && Objects.equals(description, that.description) && Objects.equals(requestStatus, that.requestStatus) && Objects.equals(author, that.author) && Objects.equals(helper, that.helper) && Objects.equals(creationDate, that.creationDate) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestType, description, requestStatus, author, helper, creationDate, user);
    }
}
