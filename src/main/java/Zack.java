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
                    System.out.println("    " + (i + 1) + ". " + tasks[i].toDisplayString());
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

            if (input.startsWith("todo ")) {
                String desc = input.substring(5);
                tasks[count] = new Todo(desc);
                count++;

                System.out.println("  " + line);
                System.out.println("    Got it. I've added this task:");
                System.out.println("    " + tasks[count - 1].toDisplayString());
                System.out.println("    Now you have " + count + " tasks in the list.");
                System.out.println("  " + line);
                continue;
            }

            if (input.startsWith("deadline ")) {
                String rest = input.substring(9);
                String[] parts = rest.split("/by", 2);
                String desc = parts[0];
                String by = parts[1];
                tasks[count] = new Deadline(desc, by);
                count++;

                System.out.println(" " + line);
                System.out.println(" Got it. I've added this task:");
                System.out.println(" " + tasks[count - 1].toDisplayString());
                System.out.println(" Now you have " + count + " tasks in the list.");
                System.out.println(" " + line);
                continue;
            }

            if (input.startsWith("event ")) {
                String rest = input.substring(6);
                String[] firstSplit = rest.split("/from", 2);
                String desc = firstSplit[0];

                String[] secondSplit = firstSplit[1].split("/to", 2);
                String from = secondSplit[0];
                String to = secondSplit[1];

                tasks[count] = new Event(desc, from, to);
                count++;

                System.out.println("  " + line);
                System.out.println("    Got it. I've added this task:");
                System.out.println("    " + tasks[count - 1].toDisplayString());
                System.out.println("    Now you have " + count + " tasks in the list.");
                System.out.println("  " + line);
                continue;
            }

            System.out.println("    added: " + input);
            System.out.println("  " + line);
        }
    }
}
