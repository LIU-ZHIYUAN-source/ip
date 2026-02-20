package zack.command;

import zack.model.Model;
import zack.ui.Ui;

/**
 * Represents a command that exits the application.
 */
public class ExitCommand implements Command {
    private final Ui ui;

    /**
     * Creates an ExitCommand with the given UI.
     *
     * @param ui The UI used to format output messages.
     */
    public ExitCommand(Ui ui) {
        this.ui = ui;
    }

    /**
     * Executes this command using the given model.
     *
     * @param model The model to operate on.
     * @return The result of executing this command.
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(ui.formatExit(), true);
    }
}
