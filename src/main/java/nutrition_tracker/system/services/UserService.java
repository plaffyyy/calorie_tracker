package nutrition_tracker.system.services;

import lombok.RequiredArgsConstructor;
import nutrition_tracker.system.dto.user.CreateUserRequest;
import nutrition_tracker.system.entities.user.Goal;
import nutrition_tracker.system.entities.user.User;
import nutrition_tracker.system.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void create(CreateUserRequest request) {
        userRepository.save(new User(
            request.name(),
            request.email(),
            request.age(),
            request.weight(),
            request.height(),
            Goal.goalFromString(request.goal())
        ));
    }

}
