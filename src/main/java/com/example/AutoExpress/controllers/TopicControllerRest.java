package com.example.AutoExpress.controllers;


import com.example.AutoExpress.dto.TopicDTO;
import com.example.AutoExpress.entities.Topic;
import com.example.AutoExpress.repositories.TopicRepository;
import com.example.AutoExpress.services.TopicService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicControllerRest {

    private final TopicRepository topicRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final TopicService topicService;

    public TopicControllerRest(TopicRepository topicRepository, ModelMapper modelMapper, Gson gson, TopicService topicService) {
        this.topicRepository = topicRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.topicService = topicService;
    }

    @GetMapping("/all")
    public List<TopicDTO> getAll() {
        List<TopicDTO> topicDTOs = new ArrayList<>();
        List<Topic> topics = topicRepository.findAll();

        for (Topic topic : topics) {
            topicDTOs.add(modelMapper.map(topic, TopicDTO.class));
        }

       return topicDTOs;

    }
}
