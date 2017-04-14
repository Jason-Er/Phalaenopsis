package com.phalaenopsis.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.phalaenopsis.util.Tuple;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file, Tuple tuple);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename, Tuple tuple);

    void deleteAll();

}
