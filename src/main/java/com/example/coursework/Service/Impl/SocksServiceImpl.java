package com.example.coursework.Service.Impl;

import com.example.coursework.Exeptions.ValidationException;
import com.example.coursework.Model.Socks.Color;
import com.example.coursework.Model.Socks.Size;
import com.example.coursework.Model.Socks.Socks;
import com.example.coursework.Model.Socks.SocksBatch;
import com.example.coursework.Service.FileService;
import com.example.coursework.Service.SocksService;
import com.example.coursework.Service.ValidationService;
import com.example.coursework.repository.SocksRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;
    private final ValidationService validationService;
    private final FileService fileService;

    @Value("${path.to.data.file}")
    private Path dataFilePath;

    @Override
    public void accept(SocksBatch socksBatch) {
        checkStocksBatch(socksBatch);
        socksRepository.save(socksBatch);
    }

    @Override
    public int issuance(SocksBatch socksBatch) {
        checkStocksBatch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    @Override
    public int reject(SocksBatch socksBatch) {
        checkStocksBatch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    @Override
    public int getCount(Color color, Size size, int cottonMin, int cottonMax) {
        if (!validationService.validate(color, size, cottonMin, cottonMax)) {
            throw new ValidationException();
        }

        Map<Socks, Integer> socksMap = socksRepository.getAll();
        int sum = 0;
        for (Map.Entry<Socks, Integer> socksItem : socksMap.entrySet()) {
            Socks socks = socksItem.getKey();

            if (socks.getColor().equals(color) &&
                    socks.getSize().equals(size) &&
                    socks.getCottonPart() >= cottonMin &&
                    socks.getCottonPart() <= cottonMax) {
                sum += socksItem.getValue();
            }
        }
        return sum;
    }

    @Override
    public File exportFile() throws IOException {
        return fileService.saveToFile(socksRepository.getList(), dataFilePath).toFile();
    }

    @Override
    public void importFile(MultipartFile file) throws IOException {
        List<SocksBatch> socksBatchList = fileService.uploadFromFile(file, dataFilePath, new TypeReference<>() {
        });
        socksRepository.replace(socksBatchList);
    }

    private void checkStocksBatch(SocksBatch socksBatch) {
        if (!validationService.validate((socksBatch))) {
            throw new ValidationException();
        }
    }

}
