package zack.command;

import zack.exception.ZackException;
import zack.model.Model;
import zack.ui.Ui;

/**
 * Represents a command that lists all tasks currently stored in the task list.
 */
public class ListCommand implements Command {
    private final Ui ui;

    /**
     * Creates a ListCommand with the given UI.
     *
     * @param ui The UI used to format output messages.
     */
    public ListCommand(Ui ui) {
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
        return new CommandResult(ui.formatListResult(model.getTaskList()));
    }
}
