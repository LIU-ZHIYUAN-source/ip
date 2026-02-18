package zack.command;

import zack.exception.ZackException;
import zack.model.Model;
import zack.task.Event;

/**
 * Adds an event task.
 */
public class EventCommand implements Command {

    private final String fullInput;

    public EventCommand(String fullInput) {
        this.fullInput = fullInput;
    }

    @Override
    public CommandResult execute(Model model) throws ZackException {
        Event e = model.addEvent(fullInput);

        String msg = "Got it. I've added this task:\n"
                + "  " + e.toDisplayString() + "\n"
                + "Now you have " + model.size() + " tasks in the list.";
        return new CommandResult(msg);
    }
}

