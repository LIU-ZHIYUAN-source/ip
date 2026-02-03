package zack.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a deadline task with a due date.
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * Constructs a deadline task with the given description and due date.
     *
     * @param description Description of the deadline.
     * @param dueDate     Due date of the deadline.
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Returns the due date of the deadline.
     *
     * @return Due date.
     */
    public LocalDate getDueDate() {
        return this.dueDate;
    }

    /**
     * Returns the type identifier of the task.
     *
     * @return Task type.
     */
    @Override
    public String getType() {
        return "[D]";
    }

    /**
     * Returns the display string of the deadline task.
     *
     * @return Display string.
     */
    @Override
    public String toDisplayString() {
        DateTimeFormatter outFmt = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        return "[D]" + getStatus() + " " + description + " (by: " + dueDate.format(outFmt) + ")";
    }
}
