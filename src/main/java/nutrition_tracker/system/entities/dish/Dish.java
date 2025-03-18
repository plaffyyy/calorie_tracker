package nutrition_tracker.system.entities.dish;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "dishes")
@Data
public final class Dish {
    @Id
    private Long id;

    @Size(max = 255, message = "Название блюда должно быть от 0 до 255 символов")
    private String name;

    @Min(value = 0, message = "Блюдо не может не иметь калорий")
    @Max(value = 3000, message = "Блюдо не может содержать более 3000 калорий")
    private int calories;

    private double proteins;
    private double fats;
    private double carbohydrates;
}
