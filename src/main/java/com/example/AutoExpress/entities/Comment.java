package com.example.AutoExpress.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{

    private String description;

    @ManyToOne
    private UserEntity createdBy;

    private LocalDate createdOn;

    @ManyToOne
    private Discussion discussion;

    private long reputation;

    @ManyToMany(mappedBy = "likedComments", fetch = FetchType.EAGER)
    private Set<UserEntity> likedBy;

    public Comment() {
        this.likedBy = new HashSet<>();
    }


    public void addUserToLikes(UserEntity user) {
        this.likedBy.add(user);
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }

    public long getReputation() {
        return reputation;
    }

    public void setReputation(long reputation) {
        this.reputation = reputation;
    }

    public Set<UserEntity> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Set<UserEntity> likedBy) {
        this.likedBy = likedBy;
    }
}
