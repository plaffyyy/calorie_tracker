package nutrition_tracker.system.controllers.report_controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import nutrition_tracker.system.dto.report.requests.DailyReportRequest;
import nutrition_tracker.system.dto.report.response.DailyReportResponse;
import nutrition_tracker.system.entities.meal.Meal;
import nutrition_tracker.system.services.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("report")
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "Получить отчет за день")
    @GetMapping("/daily")
    public ResponseEntity<DailyReportResponse> getDailyReport(@RequestBody DailyReportRequest request) {
        return ResponseEntity.ok(reportService.getDailyReport(request.userId(), request.date()));
    }

    @Operation(summary = "Получить историю питания за период")
    @GetMapping("/history")
    public ResponseEntity<List<Meal>> getMealHistory(@RequestBody DailyReportRequest request) {
        return ResponseEntity.ok(reportService.getMealHistory(request.userId(), request.date()));
    }


}
