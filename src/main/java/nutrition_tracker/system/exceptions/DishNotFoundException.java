package nutrition_tracker.system.exceptions;

public class DishNotFoundException extends NotFoundException {
    public DishNotFoundException(String message) {
        super(message);
    }
}
