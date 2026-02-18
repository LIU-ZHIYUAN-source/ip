package zack;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import zack.command.Command;
import zack.command.CommandResult;
import zack.exception.ZackException;
import zack.model.Model;
import zack.parser.CommandParser;
import zack.storage.Storage;
import zack.task.Task;
import zack.task.TaskList;
import zack.ui.Ui;

/**
 * Main entry point for the Zack application.
 */
public class Zack {
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path DATA_FILE = DATA_DIR.resolve("zack.txt");

    private final Ui ui;
    private final Storage storage;
    private final Model model;

    /**
     * Constructs a new instance.
     */
    public Zack() {
        this.ui = new Ui();
        this.storage = new Storage(DATA_DIR, DATA_FILE);

        ArrayList<Task> loadedTasks;
        try {
            loadedTasks = storage.load();
        } catch (ZackException e) {
            ui.showError(e.getMessage());
            loadedTasks = new ArrayList<>();
        }

        TaskList tasks = new TaskList(loadedTasks);
        this.model = new Model(tasks, storage);
    }

    /**
     * Runs the main application loop.
     */
    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            CommandResult result = executeCommand(input);

            System.out.println(result.getFeedbackToUser());

            if (result.shouldExit()) {
                ui.showBye();
                break;
            }
        }
    }

    /**
     * Starts the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Zack().run();
    }

    /**
     * Executes the given user input and returns the result.
     *
     * @param input The raw user input.
     * @return The result of executing the command.
     */
    public CommandResult executeCommand(String input) {
        try {
            Command command = CommandParser.parse(input);
            return command.execute(model);
        } catch (ZackException e) {
            return new CommandResult(e.getMessage());
        }
    }

}
