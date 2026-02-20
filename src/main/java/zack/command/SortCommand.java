package zack.command;

import zack.exception.ZackException;
import zack.model.Model;
import zack.task.TaskList;
import zack.ui.Ui;

/**
 * Represents a command that sorts tasks by date.
 */
public class SortCommand implements Command {

    private final Ui ui;

    /**
     * Creates a SortCommand with the given UI.
     *
     * @param ui The UI used to format output messages.
     */
    public SortCommand(Ui ui) {
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
        TaskList sortedTasks = model.sort();
        return new CommandResult(ui.formatSortResult(sortedTasks));
    }
}
