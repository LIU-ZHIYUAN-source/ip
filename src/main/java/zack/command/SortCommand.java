package zack.command;

import zack.exception.ZackException;
import zack.model.Model;

public class SortCommand implements Command {

    @Override
    public CommandResult execute(Model model) throws ZackException {
        model.sort();
        return new CommandResult("Tasks sorted by date.");
    }
}
