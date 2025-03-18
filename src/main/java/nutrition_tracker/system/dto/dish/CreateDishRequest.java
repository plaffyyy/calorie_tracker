package nutrition_tracker.system.dto.dish;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Форма для создания блюда")
public record CreateDishRequest(
        @NotNull
        @Size(max = 255, message = "Название блюда не должно превышать 255 символов")
        String name,

        @Min(value = 0, message = "Калорийность не может быть отрицательной")
        int calories,

        @Min(value = 0, message = "Количество белков не может быть отрицательным")
        double proteins,

        @Min(value = 0, message = "Количество жиров не может быть отрицательным")
        double fats,

        @Min(value = 0, message = "Количество углеводов не может быть отрицательным")
        double carbs
) {
}
