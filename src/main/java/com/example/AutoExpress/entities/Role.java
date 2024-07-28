package com.example.AutoExpress.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> userEntities;

    public Role() {
    }

    public Role(RoleEnum name) {
        this.name = name;
    }

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum role) {
        this.name = role;
    }

    public List<UserEntity> getUsers() {
        return userEntities;
    }

    public void setUsers(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }
}
