package Controllers;


import Model.Color;
import Model.Size;
import Model.SocksBatch;
import Service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "API по работе с носками", description = "CRUD - операции для носок")
public class SocksController {

    @PostMapping
    @Operation(summary = "Регистрирует приход товара на склад.")
    @ApiResponse(responseCode = "200", description = "удалось произвести отпуск носков со склада")
    @ApiResponse(responseCode = "400", description = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "произошла ошибка приложения")
    public ResponseEntity<Void> accept(@RequestBody SocksBatch socksBatch) {

    }

    @PutMapping
    @Operation(summary = "Регистрирует отпуск носков со склада.")
    @ApiResponse(responseCode = "200", description = "удалось произвести отпуск носков со склада")
    @ApiResponse(responseCode = "400", description = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "произошла ошибка приложения")
    public ResponseEntity<Void> issuance(@RequestBody SocksBatch socksBatch) {
    }

    @GetMapping
    @Operation(summary = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса")
    @ApiResponse(responseCode = "200", description = "удалось произвести отпуск носков со склада")
    @ApiResponse(responseCode = "400", description = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "произошла ошибка приложения")
    public ResponseEntity<Void> getCount(@RequestParam Color color, @RequestParam Size size, @RequestParam int cottonPartMin,@RequestParam int cottonPartMax) {

    }

    @DeleteMapping
    @Operation(summary = "Регистрирует списание испорченных (бракованных) носков")
    @ApiResponse(responseCode = "200", description = "удалось произвести отпуск носков со склада")
    @ApiResponse(responseCode = "400", description = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "произошла ошибка приложения")
    public ResponseEntity<Void> reject(@RequestBody SocksBatch socksBatch) {

    }
}
