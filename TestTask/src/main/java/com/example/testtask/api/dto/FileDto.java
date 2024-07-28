package com.example.testtask.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class FileDto {

    @NonNull
    Long id;

    @NonNull
    String title;

    @NonNull
    String base64File;

    @NonNull
    @JsonProperty("creation_date")
    Instant creationDate = Instant.now();

    @NonNull
    String description;
}
