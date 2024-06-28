package com.alura_challenge.foro.services;

import com.alura_challenge.foro.exceptions.TopicException;
import com.alura_challenge.foro.exceptions.UserException;
import com.alura_challenge.foro.http.request.TopicRequest;
import com.alura_challenge.foro.http.response.TopicResponse;
import com.alura_challenge.foro.models.Topic;
import com.alura_challenge.foro.repositories.TopicRepository;
import com.alura_challenge.foro.utils.TopicUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public TopicResponse createTopic(TopicRequest topicRequest) throws UserException {
        Topic topic = TopicUtils.toTopic(topicRequest);
        topicRepository.save(topic);
        return TopicUtils.toTopicResponse(topic);
    }

    public TopicResponse updateTopic(String id, TopicRequest topicRequest) throws TopicException {
        Topic topic = topicRepository.findById(id).orElseThrow(
                () -> new TopicException("Topic not found")
        );
        topic.setTitle(topicRequest.getTitle());
        topic.setBody(topicRequest.getBody());
        topicRepository.save(topic);
        return TopicUtils.toTopicResponse(topic);
    }

    @Transactional
    public void deleteTopic(String id) throws TopicException {
        topicRepository.findById(id).orElseThrow(
                () -> new TopicException("Topic not found")
        ).setEnable(false);
    }

    public TopicResponse getTopic(String id) throws TopicException {
        return TopicUtils.toTopicResponse(topicRepository.findById(id).orElseThrow(
                () -> new TopicException("Topic not found")
        ));
    }

    public List<TopicResponse> getMyTopics(String id) {
        return topicRepository.findByAuthorIdAndEnable(id, true).stream()
                .map(TopicUtils::toTopicResponse)
                .toList();
    }

    public List<TopicResponse> getTopics() {
        return topicRepository.findByEnable(true).stream()
                .map(TopicUtils::toTopicResponse)
                .toList();
    }

    public List<TopicResponse> getAllTopics() {
        return topicRepository.findAll().stream()
                .map(TopicUtils::toTopicResponse)
                .toList();
    }

}
