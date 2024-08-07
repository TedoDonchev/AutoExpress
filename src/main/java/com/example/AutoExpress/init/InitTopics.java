package com.example.AutoExpress.init;

import com.example.AutoExpress.entities.Role;
import com.example.AutoExpress.entities.RoleEnum;
import com.example.AutoExpress.entities.Topic;
import com.example.AutoExpress.entities.TopicEnum;
import com.example.AutoExpress.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
public class InitTopics implements CommandLineRunner {

    private final TopicRepository topicRepository;

    @Autowired
    public InitTopics(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        long count = topicRepository.count();

        if (count < TopicEnum.values().length) {
            for (TopicEnum topicEnum : TopicEnum.values()) {

                if (topicRepository.findByName(topicEnum) == null) {
                    Topic topic = new Topic(topicEnum);
                    topicRepository.save(topic);
                }

            }
        }

    }
}
