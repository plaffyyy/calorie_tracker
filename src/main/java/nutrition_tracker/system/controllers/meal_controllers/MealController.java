package nutrition_tracker.system.controllers.meal_controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import nutrition_tracker.system.dto.meal.CreateMealRequest;
import nutrition_tracker.system.entities.meal.Meal;
import nutrition_tracker.system.services.MealService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meal")
public class MealController {

    private final MealService mealService;

    @Operation(summary = "Добавление приема пищи")
    @PostMapping("/create")
    public ResponseEntity<String> addMeal(@RequestBody @Validated CreateMealRequest request) {

        mealService.create(request);
        return ResponseEntity.ok("Прием пищи создан");

    }

    @Operation(summary = "Получить все приемы пищи")
    @GetMapping("/take-all")
    public ResponseEntity<List<Meal>> getMeals() {
        return ResponseEntity.of(Optional.of(mealService.getAll()));
    }


}
