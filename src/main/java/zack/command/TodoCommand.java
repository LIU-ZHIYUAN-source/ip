package zack.command;

import zack.exception.ZackException;
import zack.model.Model;
import zack.task.Todo;

/**
 * Adds a todo task.
 */
public class TodoCommand implements Command {
    private final String fullInput;

    public TodoCommand(String fullInput) {
        this.fullInput = fullInput;
    }

    @Override
    public CommandResult execute(Model model) throws ZackException {
        Todo todo = model.addTodo(fullInput);

        String msg = "Got it. I've added this task:\n"
                + "  " + todo.toDisplayString() + "\n"
                + "Now you have " + model.size() + " tasks in the list.";
        return new CommandResult(msg);
    }
}
