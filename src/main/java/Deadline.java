public class Deadline extends Task{
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getType() {
        return "[D]";
    }

    @Override
    public String toDisplayString() {
        return "[D]" + getStatus() + " " + description + " (by: " + by + ")";
    }
}
