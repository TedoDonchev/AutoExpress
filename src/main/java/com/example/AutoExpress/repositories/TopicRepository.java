package com.example.AutoExpress.repositories;

import com.example.AutoExpress.entities.Topic;
import com.example.AutoExpress.entities.TopicEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {


    Topic findByName(TopicEnum topic);

}
