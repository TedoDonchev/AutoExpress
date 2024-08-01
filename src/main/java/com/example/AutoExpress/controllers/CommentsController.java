package com.example.AutoExpress.controllers;

import com.example.AutoExpress.dto.CommentDTO;

import com.example.AutoExpress.entities.Comment;
import com.example.AutoExpress.entities.Discussion;
import com.example.AutoExpress.services.AuthService;
import com.example.AutoExpress.services.CommentService;
import com.example.AutoExpress.services.DiscussionService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/comments")
public class CommentsController {

    private final CommentService commentService;
    private final DiscussionService discussionService;
    private final AuthService authService;

    public CommentsController(CommentService commentService, DiscussionService discussionService, AuthService authService) {
        this.commentService = commentService;
        this.discussionService = discussionService;
        this.authService = authService;
    }

    @PostMapping("/create/{discussionTitle}")
    public String createComment(@PathVariable("discussionTitle") String discussionTitle, CommentDTO data) {

        Discussion discussion = discussionService.findByTitle(discussionTitle);

        commentService.createComment(data, discussion);


        return "redirect:/discussion/" + discussion.getTitle();
    }

    @PostMapping("/delete/{id}")
    public String deleteComment(@PathVariable("id") long id, HttpServletResponse http) {

        Comment c = commentService.getById(id);
        String discussionTitle = c.getDiscussion().getTitle();

        if (c.getCreatedBy().getId() == authService.getUser().getId() || authService.isAdmin()) {
            commentService.deleteCommentById(id);
        }

        return "redirect:/discussion/" + discussionTitle;
    }

}
