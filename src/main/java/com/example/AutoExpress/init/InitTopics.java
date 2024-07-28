package com.example.AutoExpress.init;

import com.example.AutoExpress.entities.Role;
import com.example.AutoExpress.entities.RoleEnum;
import com.example.AutoExpress.entities.Topic;
import com.example.AutoExpress.entities.TopicEnum;
import com.example.AutoExpress.repositories.TopicRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitTopics implements CommandLineRunner {

    private final TopicRepository topicRepository;

    public InitTopics(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        long count = topicRepository.count();

        if (count == 0) {
            for (TopicEnum topicEnum : TopicEnum.values()) {
                Topic topic = new Topic(topicEnum);

                topicRepository.save(topic);
            }
        }

    }
}
