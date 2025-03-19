package nutrition_tracker.system.controllers.user_controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import nutrition_tracker.system.dto.user.CreateUserRequest;
import nutrition_tracker.system.entities.user.User;
import nutrition_tracker.system.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Добавить пользователя")
    @PostMapping("/create")
    public ResponseEntity<String> addUser(@RequestBody @Validated CreateUserRequest request) {
        userService.create(request);
        return ResponseEntity.ok("Пользователь успешно добавлен");
    }


    @Operation(summary = "Получить всех пользователей")
    @GetMapping("/take-all")
    public ResponseEntity<List<User>> getMeals() {
        return ResponseEntity.of(Optional.of(userService.getAll()));
    }

}
