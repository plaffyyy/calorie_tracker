package nutrition_tracker.system.services;


import lombok.RequiredArgsConstructor;
import nutrition_tracker.system.dto.report.response.DailyReportResponse;
import nutrition_tracker.system.entities.dish.Dish;
import nutrition_tracker.system.entities.meal.Meal;
import nutrition_tracker.system.entities.user.User;
import nutrition_tracker.system.repositories.MealRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {

    private final MealRepository mealRepository;
    private final UserService userService;

    /**
     * Получение отчета по приему пищи за конкретный день
     * @param userId ID пользователя
     * @param reportDate Дата отчета
     * @return количество калорий и список приемов пищи за день
     */
    @Transactional(readOnly = true)
    public DailyReportResponse getDailyReport(Long userId, String reportDate) {
        LocalDate date = LocalDate.parse(reportDate);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        User user = userService.findById(userId);

        List<Meal> meals = mealRepository.findByUserAndMealDateBetween(user, startOfDay, endOfDay);
        int totalCalories = meals.stream()
                .flatMap(meal -> meal.getDishes().stream())
                .mapToInt(Dish::getCalories)
                .sum();

        boolean inDailyNorm = totalCalories <= user.getCalorieNorm();

        return new DailyReportResponse(totalCalories, meals, inDailyNorm);
    }

    /**
     * Получение истории питания пользователя за период
     * @param userId ID пользователя
     * @param reportDate дата из запроса
     * @return Список отчетов за каждый день
     */
    @Transactional(readOnly = true)
    public List<Meal> getMealHistory(Long userId, String reportDate) {
        User user = userService.findById(userId);

        LocalDate date = LocalDate.parse(reportDate);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);


        return mealRepository.findByUserAndMealDateBetween(user, startOfDay, endOfDay);
    }
}
