package nutrition_tracker.system.test_service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import nutrition_tracker.system.dto.user.CreateUserRequest;
import nutrition_tracker.system.entities.user.Goal;
import nutrition_tracker.system.entities.user.User;
import nutrition_tracker.system.exceptions.UndefinedGoalException;
import nutrition_tracker.system.exceptions.UserIsNotFoundException;
import nutrition_tracker.system.repositories.UserRepository;
import nutrition_tracker.system.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUserSuccessfully() {
        CreateUserRequest request = new CreateUserRequest("John Doe", "john@example.com", 30, 75, 175, "поддержание");

        userService.create(request);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void findUserByIdSuccessfully() {
        User user = new User("John Doe", "john@example.com", 30, 75, 175, Goal.MAINTENANCE);
        when(userRepository.getReferenceById(1L)).thenReturn(user);

        User foundUser = userService.findById(1L);

        assertNotNull(foundUser);
        assertEquals("John Doe", foundUser.getName());
    }

    @Test
    void findUserByIdNotFound() {
        when(userRepository.getReferenceById(1L)).thenThrow(new UserIsNotFoundException("Нет пользователя по этому id"));

        assertThrows(UserIsNotFoundException.class, () -> userService.findById(1L));
    }

    @Test
    void incorrectGoal() {
        CreateUserRequest user = new CreateUserRequest("John Doe", "john@example.com", 30, 75, 175, "incorrect");

        assertThrows(UndefinedGoalException.class, () -> userService.create(user));
    }
}
