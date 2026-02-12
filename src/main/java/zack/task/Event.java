package zack.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an event task with a start date and an end date.
 */
public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructs an event task with the given description and dates.
     *
     * @param description Description of the event.
     * @param startDate   Start date of the event.
     * @param endDate     End date of the event.
     */
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the start date of the event.
     *
     * @return Start date.
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Returns the end date of the event.
     *
     * @return End date.
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }


    /**
     * Returns the type identifier of the task.
     *
     * @return Task type.
     */
    @Override
    public String getType() {
        return "[E]";
    }

    @Override
    public boolean hasDate() {
        return true;
    }

    @Override
    public java.time.LocalDate getSortDate() {
        return endDate;
    }


    /**
     * Returns the display string of the event task.
     *
     * @return Display string.
     */
    @Override
    public String toDisplayString() {
        DateTimeFormatter outFmt = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        return "[E]" + getStatus() + " " + description
                + " (from: " + startDate.format(outFmt) + " to: " + endDate.format(outFmt) + ")";
    }
}
