package nutrition_tracker.system.entities.dish;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dishes")
@Data
@NoArgsConstructor
public final class Dish {

    public Dish(String name, int calories, double proteins, double fats, double carbohydrates) {
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255, message = "Название блюда должно быть от 0 до 255 символов")
    private String name;

    @Min(value = 0, message = "Блюдо не может не иметь калорий")
    @Max(value = 3000, message = "Блюдо не может содержать более 3000 калорий")
    private int calories;

    private double proteins;
    private double fats;
    private double carbohydrates;
}
