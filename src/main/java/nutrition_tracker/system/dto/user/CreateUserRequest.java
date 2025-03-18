package nutrition_tracker.system.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Форма для создания пользователя")
public record CreateUserRequest(
        @NotNull
        @Size(max = 255, message = "Имя не должно превышать 255 символов")
        String name,
        @Email
        @NotNull
        @Size(max = 255, message = "Email не должен превышать 255 символов")
        String email,
        @Min(value = 1, message = "Возраст должен быть больше 1 года")
        @Max(value = 120, message = "Возраст не может превышать 120 лет")
        int age,
        @Min(value = 30, message = "Вес должен быть не менее 30 кг")
        @Max(value = 230, message = "Вес не может превышать 230 кг")
        int weight,
        @Min(value = 100, message = "Рост должен быть не менее 100 см")
        @Max(value = 230, message = "Рост не может превышать 230 см")
        int height,
        @NotNull String goal
) {
}
