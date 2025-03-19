package nutrition_tracker.system.test_service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import nutrition_tracker.system.dto.dish.CreateDishRequest;
import nutrition_tracker.system.entities.dish.Dish;
import nutrition_tracker.system.repositories.DishRepository;
import nutrition_tracker.system.services.DishService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DishServiceTest {

    @Mock
    private DishRepository dishRepository;

    @InjectMocks
    private DishService dishService;

    @Test
    void createDishSuccessfully() {
        CreateDishRequest request = new CreateDishRequest("Salad", 150, 5, 10, 15);

        dishService.create(request);

        verify(dishRepository, times(1)).save(any(Dish.class));
    }

    @Test
    void getAllDishesSuccessfully() {
        Dish dish1 = new Dish("Salad", 150, 5, 10, 15);
        Dish dish2 = new Dish("Soup", 200, 8, 7, 15);
        when(dishRepository.findAll()).thenReturn(List.of(dish1, dish2));

        List<Dish> dishes = dishService.getAll();

        assertEquals(2, dishes.size());
        assertEquals("Salad", dishes.get(0).getName());
    }
}
