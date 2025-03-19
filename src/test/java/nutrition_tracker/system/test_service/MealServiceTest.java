package nutrition_tracker.system.test_service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import nutrition_tracker.system.dto.meal.CreateMealRequest;
import nutrition_tracker.system.entities.dish.Dish;
import nutrition_tracker.system.entities.meal.Meal;
import nutrition_tracker.system.entities.user.User;
import nutrition_tracker.system.exceptions.DishNotFoundException;
import nutrition_tracker.system.exceptions.UserIsNotFoundException;
import nutrition_tracker.system.repositories.DishRepository;
import nutrition_tracker.system.repositories.MealRepository;
import nutrition_tracker.system.repositories.UserRepository;
import nutrition_tracker.system.services.MealService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MealServiceTest {

    @Mock
    private MealRepository mealRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DishRepository dishRepository;

    @InjectMocks
    private MealService mealService;

    @Test
    void createMealSuccessfully() {
        CreateMealRequest request = new CreateMealRequest(1L, LocalDateTime.parse("2025-03-20T12:00:00"), List.of(1L, 2L));

        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Dish dish1 = new Dish("Salad", 150, 5, 10, 15);
        Dish dish2 = new Dish("Soup", 200, 8, 7, 15);
        when(dishRepository.findAllById(List.of(1L, 2L))).thenReturn(List.of(dish1, dish2));

        mealService.create(request);

        verify(mealRepository, times(1)).save(any(Meal.class));
    }

    @Test
    void createMealUserNotFound() {
        CreateMealRequest request = new CreateMealRequest(1L, LocalDateTime.parse("2025-03-20T12:00:00"), List.of(1L, 2L));

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserIsNotFoundException.class, () -> mealService.create(request));
    }

    @Test
    void createMealDishNotFound() {
        CreateMealRequest request = new CreateMealRequest(1L, LocalDateTime.parse("2025-03-20T12:00:00"), List.of(1L, 2L));

        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(dishRepository.findAllById(List.of(1L, 2L))).thenReturn(List.of());

        assertThrows(DishNotFoundException.class, () -> mealService.create(request));
    }
}
