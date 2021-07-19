package com.neighborhood.msneighborhood.entities;

import com.neighborhood.msneighborhood.enumerated.GroupBuyingStatusEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="groupbuying")
public class GroupBuying implements Serializable {

    @Id
    @Column(name="id_groupbuying")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany
    private Collection<User> userList;

    private String groupBuyingStatus;

    private Date creationDate;

    private Date purchaseDate;

    private String imgPathThAttribute;

    private Long neighborGroupId;

    private String ownerFullName;

    private Long ownerId;

    public GroupBuying() {
        super();
        this.creationDate = new Date();
        this.groupBuyingStatus = GroupBuyingStatusEnum.IN_PROGRESS.toString();
    }

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

    public Collection<User> getUserList() {
        return userList;
    }

    public void setUserList(Collection<User> userList) {
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

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getImgPathThAttribute() {
        return imgPathThAttribute;
    }

    public void setImgPathThAttribute(String imgPathThAttribute) {
        this.imgPathThAttribute = imgPathThAttribute;
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
