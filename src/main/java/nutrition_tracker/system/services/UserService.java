package nutrition_tracker.system.services;

import lombok.RequiredArgsConstructor;
import nutrition_tracker.system.dto.user.CreateUserRequest;
import nutrition_tracker.system.entities.user.Goal;
import nutrition_tracker.system.entities.user.User;
import nutrition_tracker.system.exceptions.UserIsNotFoundException;
import nutrition_tracker.system.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;


    private int calculateDailyCalorieNorm(User user) {
        double bmr = 10 * user.getWeight() + 6.25 + user.getHeight() - 5 * user.getAge() + 5;

        return switch (user.getGoal()) {
            case WEIGHT_LOSS -> (int) (bmr * 1.2 - 500);
            case MAINTENANCE -> (int) (bmr * 1.55);
            case WEIGHT_GAIN -> (int) (bmr * 1.8 + 500);
        };
    }

    /**
     * создание пользователя
     * @param request dto для создания пользователя
     */
    public User create(CreateUserRequest request) {
        User user = new User(
                request.name(),
                request.email(),
                request.age(),
                request.weight(),
                request.height(),
                Goal.goalFromString(request.goal())
        );
        user.setCalorieNorm(calculateDailyCalorieNorm(user));
        return userRepository.save(user);
    }

    /**
     * найти пользователя по id
     * @param userId id пользователя
     * @return пользователь
     * @throws UserIsNotFoundException в случае того, если нет пользователя по этому id
     */
    public User findById(Long userId) {
        Optional<User> user = Optional.of(userRepository.getReferenceById(userId));
        return user.orElseThrow(() -> new UserIsNotFoundException("Нет пользователя по этому id"));
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

}
