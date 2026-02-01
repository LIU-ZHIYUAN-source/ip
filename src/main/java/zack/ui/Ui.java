package zack.ui;

import java.util.ArrayList;
import zack.task.Task;

/**
 * Handles all user interface interactions.
 * Responsible for displaying messages to the user.
 */
public class Ui {
    String line = "____________________________________________________________";

    /**
     * Displays a horizontal separator line.
     */
    public void showLine() {
        System.out.println("  " + line);
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
     * Displays a message indicating a task added.
     *
     * @param task Task that was added.
     * @param size Current number of tasks.
     */
    public void showAdded(Task task, int size) {
        showLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + task.toDisplayString());
        System.out.println("    Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message indicating a task deleted.
     *
     * @param task Task that was deleted.
     * @param size Current number of tasks.
     */
    public void showDeleted(Task task, int size) {
        showLine();
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    " + task.toDisplayString());
        System.out.println("    Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message indicating a task marked as done.
     *
     * @param task Task that marked as done.
     */
    public void showMarkedDone(Task task) {
        showLine();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + task.toDisplayString());
        showLine();
    }

    /**
     * Displays a message indicating a task marked as not done.
     *
     * @param task Task that marked as not done.
     */
    public void showMarkedUndone(Task task) {
        showLine();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("    " + task.toDisplayString());
        showLine();
    }

    /**
     * Displays all tasks in the list.
     *
     * @param tasks List of tasks to be displayed.
     */
    public void showList(java.util.ArrayList<Task> tasks) {
        showLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i).toDisplayString());
        }
        showLine();
    }

    /**
     * Displays tasks that match a search keyword.
     *
     * @param tasks List of matching tasks.
     */
    public void showFindResult(ArrayList<Task> tasks) {
        showLine();
        System.out.println("Here are the matching tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + "." + task.toDisplayString());
            index++;
        }
        showLine();
    }
}
