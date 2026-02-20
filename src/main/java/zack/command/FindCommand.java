package zack.command;

import zack.exception.ZackException;
import zack.model.Model;
import zack.task.TaskList;
import zack.ui.Ui;

/**
 * Represents a command that finds tasks whose descriptions contain a keyword.
 */
public class FindCommand implements Command {

    private final String fullInput;
    private final Ui ui;

    /**
     * Creates a FindCommand with the given user input and UI.
     *
     * @param fullInput The full user input.
     * @param ui        The UI used to format output messages.
     */
    public FindCommand(String fullInput, Ui ui) {
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
        TaskList matchedTasks = model.find(fullInput);
        return new CommandResult(ui.formatFindResult(matchedTasks));
    }
}
