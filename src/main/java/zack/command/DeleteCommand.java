package zack.command;

import zack.exception.ZackException;
import zack.model.Model;
import zack.task.Task;
import zack.ui.Ui;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand implements Command {

    private final String fullInput;
    private final Ui ui;

    /**
     * Creates a DeadlineCommand with the given user input and UI.
     *
     * @param fullInput The full user input.
     * @param ui        The UI used to format output messages.
     */
    public DeleteCommand(String fullInput, Ui ui) {
        this.fullInput = fullInput;
        this.ui = ui;
    }

    /**
     * Executes this command using the given model.
     *
     * @param model The model to operate on.
     * @return The result of executing this command.
     * @throws ZackException If an error occurs during execution.
     */
    @Override
    public CommandResult execute(Model model) throws ZackException {
        Task removedTask = model.delete(fullInput);
        return new CommandResult(ui.formatDeleteTask(removedTask, model.size()));
    }
}
