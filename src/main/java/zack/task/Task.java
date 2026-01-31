package zack.task;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String description;
    private boolean isDone;

    /**
     * Constructs a task with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone(){
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone(){
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the completion status icon of the task.
     *
     * @return Status icon string.
     */
    public String getStatus() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns whether the task is completed.
     *
     * @return true if the task is done, otherwise false.
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the type identifier of the task.
     *
     * @return Task type.
     */
    public abstract String getType();
    /**
     * Returns the display string of the task.
     *
     * @return Display string.
     */
    public abstract String toDisplayString();
}
