package com.alura_challenge.foro.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicResponse {

    private String id;

    private String title;

    private String body;

    private LocalDate creationDate;

    private String authorName;

}