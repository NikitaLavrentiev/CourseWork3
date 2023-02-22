package Controllers;


import Service.SocksService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "API по работе с ингредиентами", description = "CRUD - операции для ингредиентов")
public class SocksController {

    @PutMapping
    public SocksService socksService () {
    }

}
