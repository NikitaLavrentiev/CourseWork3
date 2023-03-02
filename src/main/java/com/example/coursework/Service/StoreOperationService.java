package com.example.coursework.Service;

import com.example.coursework.Model.Socks.SocksBatch;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface StoreOperationService {
    void accept(SocksBatch socksBatch);

    void issuance(SocksBatch socksBatch);

    void reject(SocksBatch socksBatch);

    File exportFile() throws IOException;

    void importFile(MultipartFile file) throws IOException;
}
