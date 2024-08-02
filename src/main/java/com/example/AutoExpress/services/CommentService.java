package com.example.AutoExpress.services;

import com.example.AutoExpress.dto.CommentDTO;
import com.example.AutoExpress.entities.Comment;
import com.example.AutoExpress.entities.Discussion;
import com.example.AutoExpress.entities.UserEntity;
import com.example.AutoExpress.repositories.CommentRepository;
import com.example.AutoExpress.repositories.DiscussionRepository;
import com.example.AutoExpress.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final AuthService authService;
    private final UserRepository userRepository;


    public CommentService(CommentRepository commentRepository, ModelMapper modelMapper, AuthService authService, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.authService = authService;

        this.userRepository = userRepository;
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

    public void increaseReputation(Comment comment, UserEntity user) {

        Set<UserEntity> checkExistence = comment
                                            .getLikedBy().stream()
                                            .filter(u -> u.getId() == user.getId())
                                            .collect(Collectors.toSet());

        if (checkExistence.isEmpty()) {
            comment.setReputation(comment.getReputation() + 1);
            comment.addUserToLikes(user);
            user.addCommentToLiked(comment);
            commentRepository.save(comment);
            userRepository.save(user);
        }

    }

}
