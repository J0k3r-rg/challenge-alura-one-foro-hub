package com.alura_challenge.foro.controllers;

import com.alura_challenge.foro.exceptions.TopicException;
import com.alura_challenge.foro.exceptions.UserException;
import com.alura_challenge.foro.http.request.TopicRequest;
import com.alura_challenge.foro.services.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PreAuthorize("hasAuthority('Create_Topic')")
    @PostMapping
    public ResponseEntity<?> createTopic(@RequestBody @Valid TopicRequest topicRequest) throws UserException {
        return ResponseEntity.ok(topicService.createTopic(topicRequest));
    }

    @PreAuthorize("hasAuthority('Update_Topic')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTopic(@PathVariable String id, @RequestBody @Valid TopicRequest topicRequest) throws TopicException {
        return ResponseEntity.ok(topicService.updateTopic(id, topicRequest));
    }

    @PreAuthorize("hasAuthority('Delete_Topic')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable String id) throws TopicException {
        topicService.deleteTopic(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('Read_Topic')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getTopic(@PathVariable String id) throws TopicException {
        return ResponseEntity.ok(topicService.getTopic(id));
    }

    @PreAuthorize("hasAuthority('Read_Topic')")
    @GetMapping
    public ResponseEntity<?> getTopics() {
        return ResponseEntity.ok(topicService.getTopics());
    }

    @PreAuthorize("hasAuthority('All_Topic')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

}
