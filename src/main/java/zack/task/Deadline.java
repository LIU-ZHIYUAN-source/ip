package zack.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String getType() {
        return "[D]";
    }

    @Override
    public String toDisplayString() {
        DateTimeFormatter outFmt = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        return "[D]" + getStatus() + " " + description + " (by: " + by.format(outFmt) + ")";
    }
}
