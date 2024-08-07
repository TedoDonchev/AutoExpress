package com.example.AutoExpress.controllers;

import com.example.AutoExpress.dto.TopicDTO;
import com.example.AutoExpress.services.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/all")
    public String getAllTopics(Model model) {
        List<TopicDTO> topicNames = topicService.getAllTopicNames();

        //TO DO: Display topics on new page
        model.addAttribute("topics", topicNames);

        return "all_topics";
    }
}
