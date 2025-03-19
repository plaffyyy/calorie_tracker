package nutrition_tracker.system.repositories;

import nutrition_tracker.system.entities.meal.Meal;
import nutrition_tracker.system.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByUserAndMealDateBetween(User user, LocalDateTime mealDateAfter, LocalDateTime mealDateBefore);
}
