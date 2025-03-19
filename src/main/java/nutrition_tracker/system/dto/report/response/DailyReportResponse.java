package nutrition_tracker.system.dto.report.response;

import io.swagger.v3.oas.annotations.media.Schema;
import nutrition_tracker.system.entities.meal.Meal;

import java.util.List;

@Schema(description = "Форма получения ежедневного отчета")
public record DailyReportResponse(
        int totalCalories,
        List<Meal> meals,
        boolean inDailyNorm
) {
}
