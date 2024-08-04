package com.example.AutoExpress.controllers;

import com.example.AutoExpress.dto.DiscussionDTO;
import com.example.AutoExpress.dto.TopicDTO;
import com.example.AutoExpress.entities.Comment;
import com.example.AutoExpress.entities.Discussion;
import com.example.AutoExpress.entities.Topic;
import com.example.AutoExpress.services.CommentService;
import com.example.AutoExpress.services.DiscussionService;
import com.example.AutoExpress.services.TopicService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/discussion")
public class DiscussionController {

    private final TopicService topicService;

    private final CommentService commentService;
    private final DiscussionService discussionService;
    private final ModelMapper modelMapper;

    public DiscussionController(TopicService topicService, CommentService commentService, DiscussionService discussionService, ModelMapper modelMapper) {
        this.topicService = topicService;
        this.commentService = commentService;
        this.discussionService = discussionService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("discussionData")
    public DiscussionDTO createEmptyDTO() {
        return new DiscussionDTO();
    }

    @GetMapping("/create")
    public String viewCreateDiscussion(Model model) {

        return "create-discussion";
    }

    @PostMapping("/create")
    public String doCreateDiscussion(@Valid DiscussionDTO data, BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("discussionData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.discussionData", bindingResult);

            return "redirect:/discussion/create";
        }

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

    @GetMapping("/by_topic/{topicName}")
    public String viewDiscussionsByTopic(@PathVariable("topicName") String topicName, Model model) {

        Topic t = topicService.getByName(topicName);

        List<Discussion> discussions = discussionService.getAllByTopic(t);

        model.addAttribute("topic", t);
        model.addAttribute("discussions", discussions);

        return "discussions_by_topic";
    }

    @PostMapping("/by_topic")
    public String byTopic(TopicDTO data) {

        return "redirect:/discussion/by_topic/" + data.getName();
    }




}
