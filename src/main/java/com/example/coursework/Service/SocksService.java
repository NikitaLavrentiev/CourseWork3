package com.example.coursework.Service;

import com.example.coursework.Model.Socks.Color;
import com.example.coursework.Model.Socks.Size;
import com.example.coursework.Model.Socks.SocksBatch;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface SocksService {
    void accept (SocksBatch socksBatch);

    int issuance (SocksBatch socksBatch);

    int reject(SocksBatch socksBatch);

    int getCount(Color color, Size size, int cottonMin, int cottonMax);

    File exportFile() throws IOException;

    void importFile(MultipartFile file) throws IOException;
}
