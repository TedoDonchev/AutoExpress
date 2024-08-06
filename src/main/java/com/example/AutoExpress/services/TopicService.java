package com.example.AutoExpress.services;

import com.example.AutoExpress.dto.TopicDTO;
import com.example.AutoExpress.entities.Topic;
import com.example.AutoExpress.entities.TopicEnum;
import com.example.AutoExpress.repositories.TopicRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    private final RestClient restClient;

    public TopicService(TopicRepository topicRepository, RestClient restClient) {
        this.topicRepository = topicRepository;
        this.restClient = restClient;
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic getByName(String name){
        return topicRepository.findByName(TopicEnum.valueOf(name));
    }

    public List<TopicDTO> getAllTopicNames() {
        return restClient.get()
                .uri("/api/topics/all")
                //.accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<TopicDTO>>() {});
    }

}
