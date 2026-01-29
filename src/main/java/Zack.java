import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


public class Zack {
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path DATA_FILE = DATA_DIR.resolve("zack.txt");

    public static void main(String[] args) {

        String line = "____________________________________________________________";


        ArrayList<Task> tasks = loadTasks();


        System.out.println("  " + line);
        System.out.println("    Hello! I'm Zack");
        System.out.println("    What can I do for you?");
        System.out.println("  " + line);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            try {

                if (input.equals("blah")) {
                    throw new ZackException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                if (input.equals("bye")) {
                    System.out.println("  " + line);
                    System.out.println("    Bye. Hope to see you again soon!");
                    System.out.println("  " + line);
                    break;
                }

                if (input.equals("list")) {
                    System.out.println("  " + line);
                    for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks.get(i).toDisplayString());
                    }
                    System.out.println("  " + line);
                    continue;
                }

                if (input.startsWith("mark ")) {
                    int idx = Integer.parseInt(input.substring(5)) - 1;
                    tasks.get(idx).markDone();
                    saveTasks(tasks);
                    System.out.println("  " + line);
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("    " + tasks.get(idx).toDisplayString());
                    System.out.println("  " + line);
                    continue;
                }

                if (input.startsWith("unmark ")) {
                    int idx = Integer.parseInt(input.substring(7)) - 1;
                    tasks.get(idx).markNotDone();
                    saveTasks(tasks);
                    System.out.println("  " + line);
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println("    " + tasks.get(idx).toDisplayString());
                    System.out.println("  " + line);
                    continue;
                }

                if (input.startsWith("todo ")) {
                    String desc = input.substring(5);
                    tasks.add(new Todo(desc));

                    saveTasks(tasks);

                    System.out.println("  " + line);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("    " + tasks.get(tasks.size() - 1).toDisplayString());
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("  " + line);
                    continue;
                }

                if (input.equals("todo")) {
                    throw new ZackException("OOPS!!! The description of a todo cannot be empty.");
                }

                if (input.startsWith("deadline ")) {
                    String rest = input.substring(9);
                    String[] parts = rest.split("/by", 2);
                    String desc = parts[0];
                    String by = parts[1];
                    tasks.add(new Deadline(desc, by));

                    saveTasks(tasks);

                    System.out.println("  " + line);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("    " + tasks.get(tasks.size() - 1).toDisplayString());
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("  " + line);
                    continue;
                }

                if (input.startsWith("event ")) {
                    String rest = input.substring(6);
                    String[] firstSplit = rest.split("/from", 2);
                    String desc = firstSplit[0];

                    String[] secondSplit = firstSplit[1].split("/to", 2);
                    String from = secondSplit[0];
                    String to = secondSplit[1];

                    tasks.add(new Event(desc, from, to));

                    saveTasks(tasks);

                    System.out.println("  " + line);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("    " + tasks.get(tasks.size() - 1).toDisplayString());
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("  " + line);
                    continue;
                }

                if (input.startsWith("delete ")) {

                    int idx = Integer.parseInt(input.substring(7)) - 1;

                    Task removed = tasks.remove(idx);
                    saveTasks(tasks);

                    System.out.println("  " + line);
                    System.out.println("    Noted. I've removed this task:");
                    System.out.println("    " + removed.toDisplayString());
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("  " + line);
                    continue;
                }

                System.out.println("    added: " + input);
                System.out.println("  " + line);

            } catch (ZackException e) {
                System.out.println("  " + line);
                System.out.println("    " + e.getMessage());
                System.out.println("  " + line);
            }
        }
    }
    private static void saveTasks(ArrayList<Task> tasks) throws ZackException {
        try{
            Files.createDirectories(DATA_DIR);
            try (BufferedWriter writer = Files.newBufferedWriter(DATA_FILE)) {
                for (Task t : tasks) {
                    writer.write(encodeTask(t));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new ZackException("OOPS!!! I couldn't save your tasks to disk.");
        }
    }

    private static String encodeTask(Task t) {
        String done = t.isDone() ? "1" : "0";

        if (t instanceof Todo) {
            return "T | " + done + " | " + t.getDescription();
        }
        if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return "D | " + done + " | " + d.getDescription() + " | " + d.getBy();
        }
        Event e = (Event) t;
        return "E | " + done + " | " + e.getDescription() + " | " + e.getFrom() + " | " + e.getTo();
    }

    private static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (!Files.exists(DATA_FILE)) {
                return tasks;
            }

            List<String> lines = Files.readAllLines(DATA_FILE);
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                Task t = decodeTask(line);
                if (t != null) {
                    tasks.add(t);
                }
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }

        return tasks;
    }

    private static Task decodeTask(String line) {
        String[] parts = line.split("\\s*\\|\\s*");

        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        boolean done = "1".equals(parts[1]);
        String desc = parts[2];

        Task t;

        switch (type) {
            case "T":
                t = new Todo(desc);
                break;

            case "D":
                if (parts.length < 4) return null;
                t = new Deadline(desc, parts[3]);
                break;

            case "E":
                if (parts.length < 5) return null;
                t = new Event(desc, parts[3], parts[4]);
                break;

            default:
                return null;
        }

        if (done) {
            t.markDone();
        }

        return t;
    }


}
