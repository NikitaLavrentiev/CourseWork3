package com.example.coursework.Service.Impl;

import com.example.coursework.Service.FileService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final ObjectMapper objectMapper;

    @Override
    public <T> Path saveToFile(T content, Path path) throws IOException {
        String json = objectMapper.writeValueAsString(content);
        createNewFile(path);
        return Files.writeString(path, json);
    }

    @Override
    public <T> List<T> uploadFromFile(MultipartFile file, Path path, TypeReference typeReference) throws IOException {
        uploadFile(file, path);
        return readListFromFile(path, typeReference);
    }

    private void uploadFile(MultipartFile file, Path filepath) throws IOException {
        Files.createDirectories(filepath.getParent());
        try {
            Files.deleteIfExists(filepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (
                InputStream is = file.getInputStream();
                OutputStream os = Files.newOutputStream(filepath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createNewFile(Path path) throws IOException {
        Files.deleteIfExists(path);
        Files.createFile(path);
    }

    private <T> List<T> readListFromFile(Path path, TypeReference<List<T>> typeReference) throws IOException {
        String json = Files.readString(path);

        if (json.isEmpty()) {
            return new ArrayList<>();
        }

        return objectMapper.readValue(json, typeReference);
    }
}
