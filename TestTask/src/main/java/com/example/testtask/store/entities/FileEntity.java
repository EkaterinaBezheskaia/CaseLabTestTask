package com.example.testtask.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "files_base")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @Builder.Default
    String base64File = "";

    @Builder.Default
    Instant creationDate = Instant.now();

    @Builder.Default
    private String description = "";

}
