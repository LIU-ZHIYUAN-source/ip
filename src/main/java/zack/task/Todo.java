package zack.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task with the given description.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type identifier of the task.
     *
     * @return Task type.
     */
    @Override
    public String getType() {
        return "[T]";
    }

    /**
     * Returns the display string of the todo task.
     *
     * @return Display string.
     */
    @Override
    public String toDisplayString() {
        return "[T]" + getStatus() + " " + description;
    }
}
