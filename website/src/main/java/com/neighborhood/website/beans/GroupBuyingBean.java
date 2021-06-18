package com.neighborhood.website.beans;

import java.util.Collection;
import java.util.Date;

public class GroupBuyingBean {

    private Long id;

    private String title;

    private Collection<UserBean> userList;

    private String groupBuyingStatus;

    private Date creationDate;

    private Date purchaseDate;

    private String imgPathThAttribute;

    private Long neighborGroupId;

    private String ownerFullName;

    private Long ownerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<UserBean> getUserList() {
        return userList;
    }

    public void setUserList(Collection<UserBean> userList) {
        this.userList = userList;
    }

    public String getGroupBuyingStatus() {
        return groupBuyingStatus;
    }

    public void setGroupBuyingStatus(String groupBuyingStatus) {
        this.groupBuyingStatus = groupBuyingStatus;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public String getImgPathThAttribute() {
        return imgPathThAttribute;
    }

    public void setImgPathThAttribute(String imgPathThAttribute) {
        this.imgPathThAttribute = imgPathThAttribute;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getNeighborGroupId() {
        return neighborGroupId;
    }

    public void setNeighborGroupId(Long neighborGroupId) {
        this.neighborGroupId = neighborGroupId;
    }

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public void setOwnerFullName(String ownerFullName) {
        this.ownerFullName = ownerFullName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
