package com.alura_challenge.foro.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @UuidGenerator
    private String id;

    private String title;

    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Temporal(TemporalType.DATE)
    @Builder.Default
    private LocalDate creationDate = LocalDate.now();

    @Builder.Default
    private boolean enable=true;

}