package zack.command;

import zack.exception.ZackException;
import zack.model.Model;
import zack.task.TaskList;

/**
 * Sorts tasks by date.
 */
public class SortCommand implements Command {

    @Override
    public CommandResult execute(Model model) throws ZackException {
        TaskList sortedTasks = model.sort();

        StringBuilder stringBuilder = new StringBuilder("Tasks sorted by date:\n");
        for (int i = 0; i < sortedTasks.size(); i++) {
            stringBuilder.append(i + 1)
                    .append(". ")
                    .append(sortedTasks.get(i).toDisplayString())
                    .append("\n");
        }

        return new CommandResult(stringBuilder.toString().trim());
    }
}
