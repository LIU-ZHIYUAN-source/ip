package zack.command;

import zack.model.Model;

/**
 * Exits the application.
 */
public class ExitCommand implements Command {

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Bye! Hope to see you again soon.", true);
    }
}
