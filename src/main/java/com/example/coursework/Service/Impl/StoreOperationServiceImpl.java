package com.example.coursework.Service.Impl;

import com.example.coursework.Model.Operation.OperationType;
import com.example.coursework.Model.Operation.StoreOperation;
import com.example.coursework.Model.Socks.SocksBatch;
import com.example.coursework.Service.FileService;
import com.example.coursework.Service.StoreOperationService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreOperationServiceImpl implements StoreOperationService {

    private final FileService fileService;
    private List<StoreOperation> operationList = new ArrayList<>();
    @Value("${path.to.data.file}")
    private Path dataFilePath;

    @Override
    public void accept(SocksBatch socksBatch) {
        operationList.add(new StoreOperation(OperationType.ACCEPT, socksBatch));
    }

    @Override
    public void issuance(SocksBatch socksBatch) {
        operationList.add(new StoreOperation(OperationType.ISSUANCE, socksBatch));
    }

    @Override
    public void reject(SocksBatch socksBatch) {
        operationList.add(new StoreOperation(OperationType.REJECT, socksBatch));
    }

    @Override
    public File exportFile() throws IOException {
        return fileService.saveToFile(operationList, dataFilePath).toFile();

    }

    @Override
    public void importFile(MultipartFile file) throws IOException {
        operationList = fileService.uploadFromFile(file, dataFilePath, new TypeReference<>() {
        });
    }
}