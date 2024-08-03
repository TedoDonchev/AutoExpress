package com.example.AutoExpress.controllers;

import com.example.AutoExpress.entities.Comment;
import com.example.AutoExpress.entities.Discussion;
import com.example.AutoExpress.entities.UserEntity;
import com.example.AutoExpress.services.CommentService;
import com.example.AutoExpress.services.DiscussionService;
import com.example.AutoExpress.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final DiscussionService discussionService;
    private final CommentService commentService;

    public UserController(UserService userService, DiscussionService discussionService, CommentService commentService) {
        this.userService = userService;
        this.discussionService = discussionService;
        this.commentService = commentService;
    }

    @GetMapping("/{username}")
    public String viewUser(@PathVariable("username") String username, Model model) {

        UserEntity user = userService.getUserByUserName(username);
        List<Discussion> discussions = discussionService.getAllByUserId(user.getId());
        Set<Comment> likedComments = user.getLikedComments();

        model.addAttribute("user", user);
        model.addAttribute("discussions", discussions);
        model.addAttribute("comments", likedComments);

        return "userProfile";

    }

}
