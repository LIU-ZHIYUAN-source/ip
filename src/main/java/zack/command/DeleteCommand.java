package zack.command;

import zack.exception.ZackException;
import zack.model.Model;
import zack.task.Task;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand implements Command {

    private final String fullInput;

    public DeleteCommand(String fullInput) {
        this.fullInput = fullInput;
    }

    @Override
    public CommandResult execute(Model model) throws ZackException {
        Task removed = model.delete(fullInput);

        String msg = "Noted. I've removed this task:\n"
                + "  " + removed.toDisplayString() + "\n"
                + "Now you have " + model.size() + " tasks in the list.";
        return new CommandResult(msg);
    }
}
