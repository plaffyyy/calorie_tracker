package nutrition_tracker.system.entities.meal;

import jakarta.persistence.*;
import lombok.Data;
import nutrition_tracker.system.entities.dish.Dish;
import nutrition_tracker.system.entities.user.User;

import java.util.List;

@Entity
@Table(name = "meals")
@Data
public final class Meal {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "meal_dishes",
            joinColumns = @JoinColumn(name = "meal_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "dish_id", nullable = false)
    )
    private List<Dish> dishes;
}
