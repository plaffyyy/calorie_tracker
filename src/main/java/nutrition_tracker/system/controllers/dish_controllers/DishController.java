package nutrition_tracker.system.controllers.dish_controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import nutrition_tracker.system.dto.dish.CreateDishRequest;
import nutrition_tracker.system.entities.dish.Dish;
import nutrition_tracker.system.services.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dish")
public class DishController {

    private final DishService dishService;

    @Operation(summary = "Добавить блюдо")
    @PostMapping("/create")
    public ResponseEntity<String> addDish(@RequestBody @Validated CreateDishRequest request) {
        dishService.create(request);
        return ResponseEntity.ok("Блюдо успешно добавлено");
    }

    @Operation(summary = "Получить все блюда")
    @GetMapping("/take-all")
    public ResponseEntity<List<Dish>> getDishes() {
        return ResponseEntity.of(Optional.of(dishService.getAll()));
    }

}
