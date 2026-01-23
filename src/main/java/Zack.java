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
        String[] items = new String[100];
        int count = 0;

        System.out.println("  " + line);
        System.out.println(" Hello! I'm Zack");
        System.out.println(" What can I do for you?");
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
                    System.out.println("    " + (i + 1) + ". " + items[i]);
                }
                System.out.println("  " + line);
                continue;
            }

            items[count] = input;
            count++;

            System.out.println("    added: " + input);
            System.out.println("  " + line);
        }
    }
}
