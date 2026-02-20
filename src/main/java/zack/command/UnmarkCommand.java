package zack.command;

import zack.exception.ZackException;
import zack.model.Model;
import zack.task.Task;
import zack.ui.Ui;

/**
 * Represents a command that unmarks a task (sets it as not done).
 */
public class UnmarkCommand implements Command {

    private final String fullInput;
    private final Ui ui;

    /**
     * Creates an UnmarkCommand with the given user input and UI.
     *
     * @param fullInput The full user input.
     * @param ui        The UI used to format output messages.
     */
    public UnmarkCommand(String fullInput, Ui ui) {
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
        Task task = model.unmark(fullInput);
        return new CommandResult(ui.formatUnmark(task));
    }
}
