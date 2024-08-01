package com.example.AutoExpress.services;

import com.example.AutoExpress.entities.Topic;
import com.example.AutoExpress.entities.TopicEnum;
import com.example.AutoExpress.repositories.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic getByName(String name){
        return topicRepository.findByName(TopicEnum.valueOf(name));
    }
}
