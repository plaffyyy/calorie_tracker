package nutrition_tracker.system.services;

import lombok.RequiredArgsConstructor;
import nutrition_tracker.system.dto.meal.CreateMealRequest;
import nutrition_tracker.system.entities.dish.Dish;
import nutrition_tracker.system.entities.meal.Meal;
import nutrition_tracker.system.entities.user.User;
import nutrition_tracker.system.exceptions.DishNotFoundException;
import nutrition_tracker.system.exceptions.UserIsNotFoundException;
import nutrition_tracker.system.repositories.DishRepository;
import nutrition_tracker.system.repositories.MealRepository;
import nutrition_tracker.system.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class MealService {

    private final MealRepository mealRepository;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;

    /**
     * создание приема пищи
     * @param request dto для создания приема пищи
     * @throws UserIsNotFoundException если пользователь не найден
     * @throws DishNotFoundException если ни одно блюдо не найдено
     */
    public void create(CreateMealRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserIsNotFoundException("Пользователь не найден"));

        List<Dish> dishes = dishRepository.findAllById(request.dishIds());
        if (dishes.isEmpty()) {
            throw new DishNotFoundException("Блюда не найдены");
        }

        mealRepository.save(new Meal(
            request.mealTime(),
            user,
            dishes
        ));
    }

    @Transactional(readOnly = true)
    public List<Meal> getAll() {
        return mealRepository.findAll();
    }

}
