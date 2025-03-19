package nutrition_tracker.system.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nutrition_tracker.system.entities.meal.Meal;

import java.util.List;

@Entity
@Table(name = "users")
@Setter @Getter
@NoArgsConstructor
public final class User {
    public User(String name, String email, int age, int weight, int height, Goal goal) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255, message = "Name не должен быть больше 255 элементов")
    private String name;

    @Email
    @NotNull
    @Size(max = 255, message = "Email не должен быть больше 255 элементов")
    private String email;

    @Min(value = 1, message = "Пользователь должен быть старше 1 года")
    @Max(value = 120, message = "Пользователь не может быть старше 120 лет")
    private int age;

    @Min(value = 30, message = "Пользователь не может быть меньше 30 килограмм")
    @Max(value = 230, message = "Пользователь не может весить больше 230 килограмм")
    private int weight;

    @Min(value = 100, message = "Пользователь не может быть ниже 100 сантиметров")
    @Max(value = 230, message = "Пользователь не может быть выше 230 сантиметров")
    private int height;

    @Enumerated(EnumType.STRING)
    private Goal goal;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Meal> meals;

    private int calorieNorm;

}
