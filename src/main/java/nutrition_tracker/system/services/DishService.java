package nutrition_tracker.system.services;

import lombok.RequiredArgsConstructor;
import nutrition_tracker.system.dto.dish.CreateDishRequest;
import nutrition_tracker.system.entities.dish.Dish;
import nutrition_tracker.system.repositories.DishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DishService {

    private final DishRepository dishRepository;

    /**
     * создание блюда
     * @param request dto для создания блюда
     */
    public void create(CreateDishRequest request) {
        dishRepository.save(new Dish(
            request.name(),
            request.calories(),
            request.proteins(),
            request.fats(),
            request.carbs()
        ));
    }

    /**
     * получить все блюда, чтобы было проще ориентироваться
     * @return List<Dish> все блюда
     */
    @Transactional(readOnly = true)
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

}
