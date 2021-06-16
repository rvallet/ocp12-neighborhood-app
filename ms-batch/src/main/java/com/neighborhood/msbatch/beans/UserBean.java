package com.neighborhood.msbatch.beans;

import java.util.Collection;
import java.util.Date;

public class UserBean {

    private Long id;

    private String lastName;

    private String firstName;

    private String email;

    private String password;

    private String role;

    private String resetToken;

    private Date creationDate;

    private AddressBean address;

    private NeighborGroupBean neighborGroup;

    private Collection<LoanBean> loanList;

    private Collection<ServiceRequestBean> serviceRequestList;

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

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
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

    public NeighborGroupBean getNeighborGroup() {
        return neighborGroup;
    }

    public void setNeighborGroup(NeighborGroupBean neighborGroup) {
        this.neighborGroup = neighborGroup;
    }

    public Collection<LoanBean> getLoanList() {
        return loanList;
    }

    public void setLoanList(Collection<LoanBean> loanList) {
        this.loanList = loanList;
    }

    public Collection<ServiceRequestBean> getServiceRequestList() {
        return serviceRequestList;
    }

    public void setServiceRequestList(Collection<ServiceRequestBean> serviceRequestList) {
        this.serviceRequestList = serviceRequestList;
    }
}
