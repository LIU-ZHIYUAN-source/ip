package zack.command;

import zack.exception.ZackException;
import zack.model.Model;
import zack.task.Deadline;
import zack.ui.Ui;

/**
 * Adds a deadline task.
 */
public class DeadlineCommand implements Command {

    private final String fullInput;
    private final Ui ui;

    /**
     * Creates a DeadlineCommand with the given user input and UI.
     *
     * @param fullInput The full user input.
     * @param ui        The UI used to format output messages.
     */
    public DeadlineCommand(String fullInput, Ui ui) {
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
        Deadline deadline = model.addDeadline(fullInput);
        String msg = ui.formatAddDeadline(deadline, model.size());
        return new CommandResult(msg);
    }
}
