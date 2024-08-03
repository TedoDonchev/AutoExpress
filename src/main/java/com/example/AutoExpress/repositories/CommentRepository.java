package com.example.AutoExpress.repositories;

import com.example.AutoExpress.entities.Comment;
import com.example.AutoExpress.entities.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByDiscussionId(long id);

    List<Comment> findAllByCreatedById(long id);
}
