package com.example.AutoExpress.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "topics")
public class Topic extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TopicEnum name;

    @OneToMany(mappedBy = "topic")
    private Set<Discussion> discussions;

    public Topic() {
    }

    public TopicEnum getName() {
        return name;
    }

    public void setName(TopicEnum name) {
        this.name = name;
    }

    public Set<Discussion> getDiscussions() {
        return discussions;
    }

    public void setDiscussions(Set<Discussion> discussions) {
        this.discussions = discussions;
    }
}
