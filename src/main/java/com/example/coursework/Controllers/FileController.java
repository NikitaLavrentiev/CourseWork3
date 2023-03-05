package com.example.coursework.Controllers;

import com.example.coursework.Service.SocksService;
import com.example.coursework.Service.StoreOperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/socks/files")
@RequiredArgsConstructor
@Tag(name = "Работа с файлами",
        description = "Импорт и экспорт файлов с даннами по носкам на складе")
public class FileController {

    private final SocksService socksService;
    private final StoreOperationService storeOperationService;

    @Operation(
            summary = "Скачать файл с БД носков на складе",
            description = "Файл загрузится в формате .json"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Файл успешно загрузился."
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Файл для загрузки не существует"
                    )
            }
    )
    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> downloadSocksJson() {
        try {
            File socksFile = socksService.exportFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(socksFile));

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(socksFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + socksFile.getName())
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(
            summary = "Закачать файл на сервер",
            description = "Подходят только файлы формата .json с носочками"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "База носков успешно обновлена"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Файл не принят на сервер, либо ошибка сервера. Обратитесь к администратору"
            )
    })
    @GetMapping(value = "/import", consumes = "multipart/from-data")
    public ResponseEntity<String> uploadSocksJson(@RequestParam MultipartFile file) {
        try {
            socksService.importFile(file);
            return ResponseEntity.ok("Файл успешно загружен");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Скачать файл с операциями произведенными на складе",
            description = "Файл загрузится в формате .json"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Файл успешно загрузился."
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Файл для загрузки не существует"
                    )
            }
    )
    @GetMapping("/operations/export")
    public ResponseEntity<InputStreamResource> downloadOperations() {
        try {
            File socksFile = storeOperationService.exportFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(socksFile));

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(socksFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + socksFile.getName())
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(
            summary = "Загрузить файл на сервер",
            description = "Подходят только файлы формата .json с носками"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "База носков успешно обновлена"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Файл не принят на сервер, либо ошибка сервера. Обратитесь к администратору"
            )
    })
    @GetMapping(value = "/operations/import", consumes = "multipart/from-data")
    public ResponseEntity<String> uploadSocksOperations(@RequestParam MultipartFile file) {
        try {
            storeOperationService.importFile(file);
            return ResponseEntity.ok("Файл успешно загружен");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}