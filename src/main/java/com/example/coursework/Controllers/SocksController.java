package com.example.coursework.Controllers;


import com.example.coursework.Exeptions.ValidationException;
import com.example.coursework.Model.Socks.Color;
import com.example.coursework.Model.Socks.Size;
import com.example.coursework.Model.Socks.SocksBatch;
import com.example.coursework.Service.SocksService;
import com.example.coursework.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
@Tag(name = "API по работе с носками", description = "CRUD - операции для носок")
public class SocksController {
    private final SocksService socksService;

    @PostMapping
    @Operation(summary = "Регистрирует приход товара на склад.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "удалось произвести отпуск носков со склада"),
            @ApiResponse(
                    responseCode = "400",
                    description = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат"),
            @ApiResponse(
                    responseCode = "500",
                    description = "произошла ошибка приложения")
    })
    public ResponseEntity<ResponseDto> accept(@RequestBody SocksBatch socksBatch) {
        socksService.accept(socksBatch);
        return ResponseEntity.ok(new ResponseDto("Носки успешно добавлены на склад"));
    }

    @PutMapping
    @Operation(summary = "Регистрирует отпуск носков со склада.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "удалось произвести отпуск носков со склада"),
            @ApiResponse(
                    responseCode = "400",
                    description = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат"),
            @ApiResponse(
                    responseCode = "500",
                    description = "произошла ошибка приложения")
    })
    public ResponseEntity<ResponseDto> issuance(@RequestBody SocksBatch socksBatch) {
        int stockCount = socksService.issuance(socksBatch);
        int quantity;
        try {
            quantity = socksService.getCount(socksBatch.getSocks().getColor(),
                    socksBatch.getSocks().getSize(),
                    0,
                    100);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(new ResponseDto(stockCount + " носков отпущено со склада"));
    }

    @GetMapping
    @Operation(summary = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "удалось произвести отпуск носков со склада"),
            @ApiResponse(
                    responseCode = "400",
                    description = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат"),
            @ApiResponse(
                    responseCode = "500",
                    description = "произошла ошибка приложения")
    })
    public ResponseEntity<ResponseDto> getCount(@RequestParam Color color, @RequestParam Size size, @RequestParam int cottonPartMin, @RequestParam int cottonPartMax) {
        int socksCount = 0;
        try {
            socksCount = socksService.getCount(color, size, cottonPartMin, cottonPartMin);
            return ResponseEntity.ok(new ResponseDto("Запрошенных пар носков на складе: " + socksCount));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Operation(summary = "Регистрирует списание испорченных (бракованных) носков",
            description = "Параметры запроса передаются в теле запроса в виде JSON-объеĸта со следующими атрибутами:\n" +
                    "• color — цвет носĸов;\n" +
                    "• size — размер носĸов;\n" +
                    "• cottonPart — процентное содержание хлопĸа в составе носĸов;\n" +
                    "• quantity — ĸоличество пар носĸов, целое число больше 0.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "удалось произвести отпуск носков со склада"),
            @ApiResponse(
                    responseCode = "400",
                    description = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат"),
            @ApiResponse(
                    responseCode = "500",
                    description = "произошла ошибка приложения")
    })
    public ResponseEntity<ResponseDto> reject(@RequestBody SocksBatch socksBatch) {
        int stockCount = socksService.reject(socksBatch);
        return ResponseEntity.ok(new ResponseDto(stockCount + " носков списано со склада"));
    }
}

