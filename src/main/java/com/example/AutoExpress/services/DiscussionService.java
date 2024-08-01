package com.example.AutoExpress.services;

import com.example.AutoExpress.dto.DiscussionDTO;
import com.example.AutoExpress.entities.Discussion;
import com.example.AutoExpress.entities.Topic;
import com.example.AutoExpress.entities.TopicEnum;
import com.example.AutoExpress.entities.UserEntity;
import com.example.AutoExpress.repositories.DiscussionRepository;
import com.example.AutoExpress.repositories.TopicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DiscussionService {

    private final DiscussionRepository discussionRepository;

    private final TopicRepository topicRepository;
    private final ModelMapper modelMapper;

    private final AuthService authService;

    public DiscussionService(DiscussionRepository discussionRepository, TopicRepository topicRepository, ModelMapper modelMapper, AuthService authService) {
        this.discussionRepository = discussionRepository;
        this.topicRepository = topicRepository;
        this.modelMapper = modelMapper;
        this.authService = authService;

    }

    public List<Discussion> getAllDiscussions() {
        return discussionRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Discussion findByTitle(String title) {
        return discussionRepository.findByTitle(title);
    }

    public List<Discussion> getAllByTopic(Topic topic) {
        return discussionRepository.findAllByTopic(topic);
    }



    public boolean createDiscussion(DiscussionDTO data) {

       Optional<Discussion> optDiscussion = discussionRepository.getByTitle(data.getTitle());

       if (optDiscussion.isPresent()) {
           return false;
       }


       Discussion discussion = modelMapper.map(data, Discussion.class);

       Topic topic = topicRepository.findByName(TopicEnum.valueOf(data.getTopic()));

       UserEntity createdBy = authService.getUser();

       discussion.setTopic(topic);
       discussion.setCreatedBy(createdBy);
       discussion.setCreatedOn(LocalDate.now());

       discussionRepository.save(discussion);


       return true;
    }

}
