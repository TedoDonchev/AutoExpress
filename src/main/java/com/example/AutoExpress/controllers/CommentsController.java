package com.example.AutoExpress.controllers;

import com.example.AutoExpress.dto.CommentDTO;

import com.example.AutoExpress.entities.Discussion;
import com.example.AutoExpress.services.CommentService;
import com.example.AutoExpress.services.DiscussionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final CommentService commentService;
    private final DiscussionService discussionService;

    public CommentsController(CommentService commentService, DiscussionService discussionService) {
        this.commentService = commentService;
        this.discussionService = discussionService;
    }

    @PostMapping("/create/{discussionTitle}")
    public ResponseEntity<String> createComment(@PathVariable("discussionTitle") String discussionTitle, CommentDTO data) {

        Discussion discussion = discussionService.findByTitle(discussionTitle);

        commentService.createComment(data, discussion);


        return new ResponseEntity<>("Comment added successfully!", HttpStatus.OK);
    }

}
