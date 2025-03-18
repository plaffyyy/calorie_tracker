package nutrition_tracker.system.entities.user;

import nutrition_tracker.system.exceptions.UndefinedGoalException;

public enum Goal {
    WEIGHT_LOSS, MAINTENANCE, WEIGHT_GAIN;
    public static Goal goalFromString(String status) {
        return switch (status.toLowerCase()) {
            case ("похудение") -> Goal.WEIGHT_LOSS;
            case ("поддержание") -> Goal.MAINTENANCE;
            case ("набор массы") -> Goal.WEIGHT_GAIN;
            default -> throw new UndefinedGoalException("Нет такой цели");
        };
    }
}
