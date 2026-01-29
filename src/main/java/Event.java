import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task{
    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }


    @Override
    public String getType() {
        return "[E]";
    }

    @Override
    public String toDisplayString() {
        DateTimeFormatter outFmt = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        return "[E]" + getStatus() + " " + description
                + " (from: " + from.format(outFmt) + " to: " + to.format(outFmt) + ")";
    }
}
