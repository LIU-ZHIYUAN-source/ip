package zack.ui;

import zack.exception.ZackException;
import zack.task.Deadline;
import zack.task.Event;
import zack.task.Task;
import zack.task.TaskList;
import zack.task.Todo;

/**
 * Handles all user interface interactions.
 * Responsible for displaying messages to the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";

    /**
     * Displays a horizontal separator line.
     */
    public void showLine() {
        System.out.println("  " + LINE);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("    Hello! I'm Zack");
        System.out.println("    What can I do for you?");
        showLine();
    }

    /**
     * Displays the goodbye message.
     */
    public void showBye() {
        showLine();
        System.out.println("    Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays an error message.
     *
     * @param message Error message to be displayed.
     */
    public void showError(String message) {
        showLine();
        System.out.println("    " + message);
        showLine();
    }

    /**
     * Formats the message for adding a deadline task.
     *
     * @param deadline The deadline task added.
     * @param size     The updated number of tasks in the list.
     * @return The formatted message.
     */
    public String formatAddDeadline(Deadline deadline, int size) {
        return "Got it. I've added this task:\n"
                + "  " + deadline.toDisplayString() + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Formats the message for deleting a task.
     *
     * @param task The removed task.
     * @param size The updated number of tasks in the list.
     * @return The formatted message.
     */
    public String formatDeleteTask(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + "  " + task.toDisplayString() + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Formats the message for adding an event task.
     *
     * @param event The event task added.
     * @param size  The updated number of tasks in the list.
     * @return The formatted message.
     */
    public String formatAddEvent(Event event, int size) {
        return "Got it. I've added this task:\n"
                + "  " + event.toDisplayString() + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Formats the exit message.
     *
     * @return The formatted exit message.
     */
    public String formatExit() {
        return "Bye! Hope to see you again soon.";
    }

    /**
     * Formats the message for displaying the tasks that match the find keyword.
     *
     * @param matchedTasks The task list containing the matching tasks.
     * @return The formatted message.
     * @throws ZackException If retrieving a task fails.
     */
    public String formatFindResult(TaskList matchedTasks) throws ZackException {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchedTasks.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(matchedTasks.get(i).toDisplayString())
                    .append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Formats the message for listing all tasks.
     *
     * @param taskList The task list to display.
     * @return The formatted message.
     * @throws ZackException If retrieving a task fails.
     */
    public String formatListResult(TaskList taskList) throws ZackException {
        if (taskList.size() == 0) {
            return "Your task list is empty.";
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            sb.append(i + 1)
                    .append(". ")
                    .append(task.toDisplayString())
                    .append("\n");
        }

        return sb.toString().trim();
    }

    /**
     * Formats the message for marking a task as done.
     *
     * @param task The updated task.
     * @return The formatted message.
     */
    public String formatMark(Task task) {
        return "Nice! I've marked this task as done:\n"
                + "  " + task.toDisplayString();
    }

    /**
     * Formats the message for displaying tasks sorted by date.
     *
     * @param sortedTasks The sorted task list.
     * @return The formatted message.
     * @throws ZackException If retrieving a task fails.
     */
    public String formatSortResult(TaskList sortedTasks) throws ZackException {
        StringBuilder sb = new StringBuilder("Tasks sorted by date:\n");
        for (int i = 0; i < sortedTasks.size(); i++) {
            Task task = sortedTasks.get(i);
            sb.append(i + 1)
                    .append(". ")
                    .append(task.toDisplayString())
                    .append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Formats the message for adding a todo task.
     *
     * @param todo The todo task added.
     * @param size The updated number of tasks in the list.
     * @return The formatted message.
     */
    public String formatAddTodo(Todo todo, int size) {
        return "Got it. I've added this task:\n"
                + "  " + todo.toDisplayString() + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Formats the message for unmarking a task.
     *
     * @param task The updated task.
     * @return The formatted message.
     */
    public String formatUnmark(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + "  " + task.toDisplayString();
    }
}
