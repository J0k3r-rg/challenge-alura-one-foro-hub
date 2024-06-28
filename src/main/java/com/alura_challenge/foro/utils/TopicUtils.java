package com.alura_challenge.foro.utils;

import com.alura_challenge.foro.exceptions.UserException;
import com.alura_challenge.foro.http.request.TopicRequest;
import com.alura_challenge.foro.http.response.TopicResponse;
import com.alura_challenge.foro.models.Topic;
import com.alura_challenge.foro.models.User;
import com.alura_challenge.foro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicUtils {

    @Autowired
    private static UserRepository userRepository;

    public static TopicResponse toTopicResponse(Topic topic){
        return TopicResponse.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .body(topic.getBody())
                .authorName(topic.getAuthor().getUsername())
                .build();
    }

    public static Topic toTopic(TopicRequest topicRequest) throws UserException {
        User user = userRepository.findById(topicRequest.getAuthorId())
                .orElseThrow( () -> new UserException("User not found"));
        return Topic.builder()
                .title(topicRequest.getTitle())
                .body(topicRequest.getBody())
                .author(user)
                .build();
    }

}
