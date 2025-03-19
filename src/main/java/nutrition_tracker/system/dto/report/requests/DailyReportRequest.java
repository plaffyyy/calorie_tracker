package nutrition_tracker.system.dto.report.requests;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Форма для создания дневного отчета")
public record DailyReportRequest(
        Long userId,
        String date
) {
}
