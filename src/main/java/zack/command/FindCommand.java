package zack.command;

import zack.exception.ZackException;
import zack.model.Model;
import zack.task.TaskList;

/**
 * Finds tasks whose descriptions contain a keyword.
 */
public class FindCommand implements Command {

    private final String fullInput;

    public FindCommand(String fullInput) {
        this.fullInput = fullInput;
    }

    @Override
    public CommandResult execute(Model model) throws ZackException {
        TaskList matchedTasks = model.find(fullInput);

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchedTasks.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(matchedTasks.get(i).toDisplayString())
                    .append("\n");
        }

        return new CommandResult(sb.toString());
    }
}
