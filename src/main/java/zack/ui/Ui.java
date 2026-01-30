package zack.ui;

import zack.task.Task;

public class Ui {
    String line = "____________________________________________________________";

    public void showLine() {
        System.out.println("  " + line);
    }

    public void showWelcome() {
        showLine();
        System.out.println("    Hello! I'm Zack");
        System.out.println("    What can I do for you?");
        showLine();
    }

    public void showBye() {
        showLine();
        System.out.println("    Bye. Hope to see you again soon!");
        showLine();
    }

    public void showError(String message) {
        showLine();
        System.out.println("    " + message);
        showLine();
    }

    public void showAdded(Task task, int size) {
        showLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + task.toDisplayString());
        System.out.println("    Now you have " + size + " tasks in the list.");
        showLine();
    }

    public void showDeleted(Task task, int size) {
        showLine();
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    " + task.toDisplayString());
        System.out.println("    Now you have " + size + " tasks in the list.");
        showLine();
    }

    public void showMarkedDone(Task task) {
        showLine();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + task.toDisplayString());
        showLine();
    }

    public void showMarkedUndone(Task task) {
        showLine();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("    " + task.toDisplayString());
        showLine();
    }

    public void showList(java.util.ArrayList<Task> tasks) {
        showLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i).toDisplayString());
        }
        showLine();
    }
}
