package zack.command;

import zack.exception.ZackException;
import zack.model.Model;

/**
 * Sorts tasks by date.
 */
public class SortCommand implements Command {

    @Override
    public CommandResult execute(Model model) throws ZackException {
        model.sort();
        return new CommandResult("Tasks sorted by date.");
    }
}
