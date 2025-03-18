package nutrition_tracker.system.entities.meal;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import nutrition_tracker.system.entities.dish.Dish;
import nutrition_tracker.system.entities.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "meals")
@Data
@NoArgsConstructor
public final class Meal {

    public Meal(LocalDateTime mealDate, User user, List<Dish> dishes) {
        this.mealDate = mealDate;
        this.user = user;
        this.dishes = dishes;
    }

    @Id
    private Long id;

    @Column(name = "meal_date", nullable = false)
    private LocalDateTime mealDate;

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
