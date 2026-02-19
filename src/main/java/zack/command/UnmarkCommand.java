package zack.command;

import zack.exception.ZackException;
import zack.model.Model;
import zack.task.Task;

/**
 * Unmarks a task (sets it as not done).
 */
public class UnmarkCommand implements Command {

    private final String fullInput;

    public UnmarkCommand(String fullInput) {
        this.fullInput = fullInput;
    }

    @Override
    public CommandResult execute(Model model) throws ZackException {
        Task task = model.unmark(fullInput);
        String msg = "OK, I've marked this task as not done yet:\n"
                + "  " + task.toDisplayString();
        return new CommandResult(msg);
    }
}
