package com.example.testtask.api.factories;

import com.example.testtask.api.dto.FileDto;
import com.example.testtask.store.entities.FileEntity;
import org.springframework.stereotype.Component;

@Component
public class FileDtoFactory {

    public FileDto makeFileDto(FileEntity entity) {

        return FileDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .creationDate(entity.getCreationDate())
                .build();
    }

}
