package zack.ui;

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
}
