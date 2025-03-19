package nutrition_tracker.system.test_service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import nutrition_tracker.system.dto.report.response.DailyReportResponse;
import nutrition_tracker.system.entities.dish.Dish;
import nutrition_tracker.system.entities.meal.Meal;
import nutrition_tracker.system.entities.user.User;
import nutrition_tracker.system.repositories.MealRepository;
import nutrition_tracker.system.services.ReportService;
import nutrition_tracker.system.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @Mock
    private MealRepository mealRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ReportService reportService;

    @Test
    void getDailyReportSuccessfully() {
        Long userId = 1L;
        String reportDate = "2025-03-20";

        User user = new User();
        user.setCalorieNorm(2000);
        when(userService.findById(userId)).thenReturn(user);

        Meal meal1 = new Meal();
        meal1.setDishes(List.of(new Dish("Salad", 150, 5, 10, 15)));
        Meal meal2 = new Meal();
        meal2.setDishes(List.of(new Dish("Soup", 200, 8, 7, 15)));
        when(mealRepository.findByUserAndMealDateBetween(eq(user), any(), any())).thenReturn(List.of(meal1, meal2));

        DailyReportResponse report = reportService.getDailyReport(userId, reportDate);

        assertNotNull(report);
        assertTrue(report.inDailyNorm());
    }

    @Test
    void getMealHistorySuccessfully() {
        Long userId = 1L;
        String reportDate = "2025-03-20";

        User user = new User();
        when(userService.findById(userId)).thenReturn(user);

        Meal meal = new Meal();
        meal.setDishes(List.of(new Dish("Salad", 150, 5, 10, 15)));
        when(mealRepository.findByUserAndMealDateBetween(eq(user), any(), any())).thenReturn(List.of(meal));

        List<Meal> meals = reportService.getMealHistory(userId, reportDate);

        assertEquals(1, meals.size());
        assertEquals("Salad", meals.get(0).getDishes().get(0).getName());
    }
}
