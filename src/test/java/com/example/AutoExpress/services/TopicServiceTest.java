package com.example.AutoExpress.services;

import com.example.AutoExpress.entities.Topic;
import com.example.AutoExpress.entities.TopicEnum;
import com.example.AutoExpress.repositories.TopicRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.web.client.RestClient;

import java.util.List;


public class TopicServiceTest {

    private TopicRepository mockTopicRepository;
    private Topic mockTopic;
    private TopicService topicService;

    @Before
    public void init() {

        this.mockTopic = new Topic(TopicEnum.BMW);

        this.mockTopicRepository = Mockito.mock(TopicRepository.class);

        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build();

        this.topicService = new TopicService(mockTopicRepository, restClient);
    }


    @Test
    public void getAllTopicNamesShouldReturnCorrect() {

        List<Topic> expected = mockTopicRepository.findAll();

        List<Topic> actual = topicService.getAllTopics();

        Assertions.assertEquals(expected, actual);

    }

}
