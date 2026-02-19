package zack.command;

import zack.exception.ZackException;
import zack.model.Model;
import zack.task.Task;

/**
 * Marks a task as done.
 */
public class MarkCommand implements Command {

    private final String fullInput;

    public MarkCommand(String fullInput) {
        this.fullInput = fullInput;
    }

    @Override
    public CommandResult execute(Model model) throws ZackException {
        Task task = model.mark(fullInput);
        String msg = "Nice! I've marked this task as done:\n"
                + "  " + task.toDisplayString();
        return new CommandResult(msg);
    }
}

