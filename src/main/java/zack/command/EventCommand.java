package zack.command;

import zack.exception.ZackException;
import zack.model.Model;
import zack.task.Event;
import zack.ui.Ui;

/**
 * Represents a command that adds an event task.
 */
public class EventCommand implements Command {

    private final String fullInput;
    private final Ui ui;

    /**
     * Creates an EventCommand with the given user input and UI.
     *
     * @param fullInput The full user input.
     * @param ui        The UI used to format output messages.
     */
    public EventCommand(String fullInput, Ui ui) {
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
        Event event = model.addEvent(fullInput);
        return new CommandResult(ui.formatAddEvent(event, model.size()));
    }
}

