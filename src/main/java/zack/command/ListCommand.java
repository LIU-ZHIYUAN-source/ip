package zack.command;

import zack.exception.ZackException;
import zack.model.Model;
import zack.task.Task;
import zack.task.TaskList;

/**
 * Lists all tasks currently stored in the task list.
 */
public class ListCommand implements Command {
    @Override
    public CommandResult execute(Model model) throws ZackException {
        TaskList taskList = model.getTaskList();

        if (taskList.size() == 0) {
            return new CommandResult("Your task list is empty.");
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            sb.append(i + 1)
                    .append(". ")
                    .append(t.toDisplayString())
                    .append("\n");
        }

        return new CommandResult(sb.toString().trim());
    }
}
