package zack.command;

import zack.exception.ZackException;
import zack.model.Model;
import zack.task.Deadline;

/**
 * Adds a deadline task.
 */
public class DeadlineCommand implements Command {

    private final String fullInput;

    public DeadlineCommand(String fullInput) {
        this.fullInput = fullInput;
    }

    @Override
    public CommandResult execute(Model model) throws ZackException {
        Deadline deadline = model.addDeadline(fullInput);

        String msg = "Got it. I've added this task:\n"
                + "  " + deadline.toDisplayString() + "\n"
                + "Now you have " + model.size() + " tasks in the list.";
        return new CommandResult(msg);
    }
}
