package nutrition_tracker.system.exceptions;

public class UserIsNotFoundException extends RuntimeException {
    public UserIsNotFoundException(String message) {
        super(message);
    }
}
