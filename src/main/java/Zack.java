import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class Zack {
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path DATA_FILE = DATA_DIR.resolve("zack.txt");
    private static final DateTimeFormatter DATE_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Scanner scanner;

    public Zack(){
        this.ui = new Ui();
        this.storage = new Storage(DATA_DIR, DATA_FILE);

        ArrayList<Task> loaded;
        try {
            loaded = storage.load();
        } catch (ZackException e) {
            ui.showError(e.getMessage());
            loaded = new ArrayList<>();
        }
        this.tasks = new TaskList(loaded);
        this.scanner = new Scanner(System.in);
    }

    public void run(){
        ui.showWelcome();

        while (true) {
            String input = scanner.nextLine();

            try {
                if (input.equals("blah")) {
                    throw new ZackException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                if (input.equals("bye")) {
                    ui.showBye();
                    break;
                }

                if (input.equals("list")) {
                    ui.showList(tasks.getTasks());
                    continue;
                }

                if (input.startsWith("mark ")) {
                    int idx = Parser.parseIndex(input, "mark");
                    Task task = tasks.get(idx);
                    task.markDone();
                    storage.save(tasks.getTasks());
                    ui.showMarkedDone(task);
                    continue;
                }

                if (input.startsWith("unmark ")) {
                    int idx = Parser.parseIndex(input, "unmark");
                    Task task = tasks.get(idx);
                    task.markNotDone();
                    storage.save(tasks.getTasks());
                    ui.showMarkedUndone(task);
                    continue;
                }

                if (input.startsWith("todo ") || input.equals("todo")) {
                    Task task = Parser.parseTodo(input);
                    tasks.add(task);
                    storage.save(tasks.getTasks());
                    ui.showAdded(task, tasks.size());
                    continue;
                }

                if (input.startsWith("deadline ")) {
                    Task task = Parser.parseDeadline(input, DATE_FMT);
                    tasks.add(task);
                    storage.save(tasks.getTasks());
                    ui.showAdded(task, tasks.size());
                    continue;
                }

                if (input.startsWith("event ")) {
                    Task task = Parser.parseEvent(input, DATE_FMT);
                    tasks.add(task);
                    storage.save(tasks.getTasks());
                    ui.showAdded(task, tasks.size());
                    continue;
                }

                if (input.startsWith("delete ")) {
                    int idx = Parser.parseIndex(input, "delete");
                    Task removed = tasks.remove(idx);
                    storage.save(tasks.getTasks());
                    ui.showDeleted(removed, tasks.size());
                    continue;
                }

                System.out.println("    added: " + input);
                ui.showLine();

            } catch (ZackException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Zack().run();
    }
}
