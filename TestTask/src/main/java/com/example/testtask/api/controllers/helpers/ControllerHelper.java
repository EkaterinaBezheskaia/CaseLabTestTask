package com.example.testtask.api.controllers.helpers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.example.testtask.api.exceptions.NotFoundException;
import com.example.testtask.store.entities.FileEntity;
import com.example.testtask.store.repositories.FileRepository;
import org.springframework.stereotype.Component;
import jakarta.transaction.Transactional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
@Transactional

public class ControllerHelper {

    FileRepository fileRepository;

    public FileEntity getProjectOrThrowException(Long fileId) {

        return fileRepository
                .findById(fileId)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "File with id %s not found",
                                        fileId
                                )
                        )
                );
    }
}
