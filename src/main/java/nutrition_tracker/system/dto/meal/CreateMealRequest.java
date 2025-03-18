package nutrition_tracker.system.dto.meal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Форма для создания приема пищи")
public record CreateMealRequest(
        @NotNull
        Long userId,

        @NotNull
        LocalDateTime mealTime,

        @NotNull
        List<Long> dishIds
){}