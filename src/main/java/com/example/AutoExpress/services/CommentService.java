package com.example.AutoExpress.services;

import com.example.AutoExpress.dto.CommentDTO;
import com.example.AutoExpress.entities.Comment;
import com.example.AutoExpress.entities.Discussion;
import com.example.AutoExpress.repositories.CommentRepository;
import com.example.AutoExpress.repositories.DiscussionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final AuthService authService;


    public CommentService(CommentRepository commentRepository, ModelMapper modelMapper, AuthService authService) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.authService = authService;

    }


    public void createComment(CommentDTO data, Discussion discussion) {

        Comment comment = modelMapper.map(data, Comment.class);

        comment.setCreatedBy(authService.getUser());
        comment.setReputation(0);
        comment.setCreatedOn(LocalDate.now());

        comment.setDiscussion(discussion);

        commentRepository.save(comment);

    }

    public List<Comment> getAllByDiscussion(long id) {
        return commentRepository.findAllByDiscussionId(id);
    }

    public void deleteCommentById(long id) {
        Comment c = getById(id);

        commentRepository.delete(c);
    }

    public Comment getById(long id) {
        return commentRepository.findById(id).get();
    }


}
