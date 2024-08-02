package com.example.AutoExpress.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Role> roles;

    @OneToMany(mappedBy = "createdBy")
    private Set<Discussion> createdDiscussions;

    @OneToMany(mappedBy = "createdBy")
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Comment> likedComments;

    public UserEntity() {
        this.roles = new HashSet<>();
        this.createdDiscussions = new HashSet<>();
        this.comments = new ArrayList<>();
        this.likedComments = new HashSet<>();
    }

    public void addCommentToLiked(Comment c) {
        this.likedComments.add(c);
    }


    public void addRole(Role role) {
        this.roles.add(role);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Discussion> getCreatedDiscussions() {
        return createdDiscussions;
    }

    public void setCreatedDiscussions(Set<Discussion> createdDiscussions) {
        this.createdDiscussions = createdDiscussions;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<Comment> getLikedComments() {
        return likedComments;
    }

    public void setLikedComments(Set<Comment> likedComments) {
        this.likedComments = likedComments;
    }
}

