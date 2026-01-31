package zack.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an event task with a start date and an end date.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an event task with the given description and dates.
     *
     * @param description Description of the event.
     * @param from Start date of the event.
     * @param to End date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date of the event.
     *
     * @return Start date.
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Returns the end date of the event.
     *
     * @return End date.
     */
    public LocalDate getTo() {
        return this.to;
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

    /**
     * Returns the display string of the event task.
     *
     * @return Display string.
     */
    @Override
    public String toDisplayString() {
        DateTimeFormatter outFmt = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        return "[E]" + getStatus() + " " + description
                + " (from: " + from.format(outFmt) + " to: " + to.format(outFmt) + ")";
    }
}
