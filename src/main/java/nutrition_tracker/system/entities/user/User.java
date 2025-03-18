package nutrition_tracker.system.entities.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255, message = "Name не должен быть больше 255 элементов")
    private String name;

    @Email
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

}
