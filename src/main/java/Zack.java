import java.util.Scanner;

public class Zack {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        String line = "____________________________________________________________";


        Task[] tasks = new Task[100];
        int count = 0;

        System.out.println("  " + line);
        System.out.println("    Hello! I'm Zack");
        System.out.println("    What can I do for you?");
        System.out.println("  " + line);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            System.out.println("  " + line);

            if (input.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("  " + line);
                break;
            }

            if (input.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks[i].getStatus() +
                            " " + tasks[i].getDescription());
                }
                System.out.println("  " + line);
                continue;
            }

            if (input.startsWith("mark ")) {
                int idx = Integer.parseInt(input.substring(5)) - 1;
                tasks[idx].markDone();
                System.out.println("  " + line);
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("    " + tasks[idx].toDisplayString());
                System.out.println("  " + line);
                continue;
            }

            if (input.startsWith("unmark ")) {
                int idx = Integer.parseInt(input.substring(7)) - 1;
                tasks[idx].markNotDone();
                System.out.println("  " + line);
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("    " + tasks[idx].toDisplayString());
                System.out.println("  " + line);
                continue;
            }

            tasks[count] = new Task(input);
            count++;

            System.out.println("    added: " + input);
            System.out.println("  " + line);
        }
    }
}
