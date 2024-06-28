package com.alura_challenge.foro.repositories;

import com.alura_challenge.foro.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {

    List<Topic> findByAuthorIdAndEnable(String authorId, boolean enable);

    List<Topic> findByEnable(boolean enable);

}