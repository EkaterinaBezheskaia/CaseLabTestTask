package com.example.testtask.store.repositories;

import com.example.testtask.store.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.stream.Stream;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

    Optional<FileEntity> findByTitle(String title);

    Stream<FileEntity> streamAllBy();

    Stream<FileEntity> streamAllByTitleStartsWithIgnoreCase(String prefixName);

}
