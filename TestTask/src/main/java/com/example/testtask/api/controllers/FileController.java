package com.example.testtask.api.controllers;

import com.example.testtask.store.repositories.FileRepository;
import com.example.testtask.api.dto.FileDto;
import com.example.testtask.api.factories.FileDtoFactory;
import com.example.testtask.store.entities.FileEntity;
import com.example.testtask.api.controllers.helpers.ControllerHelper;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
@Component
@Repository
public class FileController {

    FileRepository fileRepository;

    FileDtoFactory fileDtoFactory;

    ControllerHelper controllerHelper;

    public static final String GET_ALL_FILES = "/api/files";
    public static final String GET_FILE = "/{id}";
    public static final String CREATE_FILE = "/api/files/{id}";


    @PostMapping(CREATE_FILE)
    public Long createFile(
            @RequestBody FileDto fileDto) throws BadRequestException {
        if (fileDto.getBase64File() == null || fileDto.getBase64File().isEmpty()) {
            throw new BadRequestException("File content can't be empty.");
        }

        FileEntity fileEntity = FileEntity.builder()
                .title(fileDto.getTitle())
                .creationDate(fileDto.getCreationDate())
                .description(fileDto.getDescription())
                .base64File(fileDto.getBase64File())
                .build();

        FileEntity savedFile = fileRepository.saveAndFlush(fileEntity);
        return savedFile.getId();
    }

    @GetMapping(GET_FILE)
    public FileDto getFile(
            @PathVariable Long id) throws BadRequestException {
        FileEntity fileEntity = fileRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("File not found"));

        return fileDtoFactory.makeFileDto(fileEntity);
    }

    @GetMapping(GET_ALL_FILES)
    public List<FileDto> getAllFiles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "creationDate") String sortBy) {

        Page<FileEntity> filesPage = fileRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending()));

        return filesPage.stream()
                .map(fileDtoFactory::makeFileDto)
                .collect(Collectors.toList());
    }

/*    @SneakyThrows
    @PutMapping(CREATE_OR_UPDATE_FILE)
    public FileDto createOrUpdateFile(
            @RequestParam(value = "file_id", required = false) Optional<Long> optionalFileId,
            @RequestParam(value = "file_title", required = false) Optional<String> optionalFileTitle) {

        optionalFileTitle = optionalFileTitle.filter(fileName -> !fileName.trim().isEmpty());

        boolean isCreate = !optionalFileId.isPresent();

        if (isCreate && !optionalFileTitle.isPresent()) {
            throw new BadRequestException("File title can't be empty.");
        }

        final FileEntity file = optionalFileId
                .map(controllerHelper::getProjectOrThrowException)
                .orElseGet(() -> FileEntity.builder().build());

        optionalFileTitle
                .ifPresent(fileTitle -> {

                    fileRepository
                            .findByTitle(fileTitle)
                            .filter(anotherFile -> !Objects.equals(anotherFile.getId(), file.getId()))
                            .ifPresent(anotherFile -> {
                                try {
                                    throw new BadRequestException(
                                            String.format("File \"%s\" already exists.", fileTitle)
                                    );
                                } catch (BadRequestException e) {
                                    throw new RuntimeException(e);
                                }
                            });

                    file.setTitle(fileTitle);
                });

        final FileEntity savedFile = fileRepository.saveAndFlush(file);

        return fileDtoFactory.makeFileDto(savedFile);
    } */
}