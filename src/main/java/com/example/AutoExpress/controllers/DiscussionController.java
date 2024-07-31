package com.example.AutoExpress.controllers;

import com.example.AutoExpress.dto.DiscussionDTO;
import com.example.AutoExpress.entities.Comment;
import com.example.AutoExpress.entities.Discussion;
import com.example.AutoExpress.entities.Topic;
import com.example.AutoExpress.services.CommentService;
import com.example.AutoExpress.services.DiscussionService;
import com.example.AutoExpress.services.TopicService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/discussion")
public class DiscussionController {

    private final TopicService topicService;

    private final CommentService commentService;
    private final DiscussionService discussionService;

    public DiscussionController(TopicService topicService, CommentService commentService, DiscussionService discussionService) {
        this.topicService = topicService;
        this.commentService = commentService;
        this.discussionService = discussionService;
    }

    @ModelAttribute("discussionData")
    public DiscussionDTO createEmptyDTO() {
        return new DiscussionDTO();
    }

    @GetMapping("/create")
    public String viewCreateDiscussion(Model model) {

//        List<Topic> topics = topicService.getAllTopics();
//
//        model.addAttribute("topics", topics);

        return "create-discussion";
    }

    @PostMapping("/create")
    public String doCreateDiscussion(@Valid DiscussionDTO data) {

        discussionService.createDiscussion(data);


        return "redirect:/";
    }



    @GetMapping("/{title}")
    public String viewDiscussion(@PathVariable("title") String title, Model model) {

        Discussion discussion = discussionService.findByTitle(title);

        List<Comment> comments = commentService.getAllByDiscussion(discussion.getId());

        model.addAttribute("comments", comments);
        model.addAttribute("discussion", discussion);

        return "discussion";
    }


}
