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

        System.out.println(" " + line);
        System.out.println(" Hello! I'm Zack");
        System.out.println(" What can I do for you?");
        System.out.println(" " + line);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            System.out.println(" " + line);

            if (input.equals("bye")) {
                System.out.println("  Bye. Hope to see you again soon!");
                System.out.println(" " + line);
                break;
            }
            System.out.println("  " + input);
            System.out.println(" " + line);
        }
    }
}
