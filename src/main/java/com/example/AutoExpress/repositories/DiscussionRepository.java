package com.example.AutoExpress.repositories;

import com.example.AutoExpress.entities.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {

    Optional<Discussion> getByTitle(String title);


    Discussion findByTitle(String title);
}
