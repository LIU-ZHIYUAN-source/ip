package zack.parser;

import zack.command.Command;
import zack.command.DeadlineCommand;
import zack.command.DeleteCommand;
import zack.command.EventCommand;
import zack.command.ExitCommand;
import zack.command.FindCommand;
import zack.command.ListCommand;
import zack.command.MarkCommand;
import zack.command.SortCommand;
import zack.command.TodoCommand;
import zack.command.UnmarkCommand;
import zack.exception.ZackException;
import zack.ui.Ui;

/**
 * Represents a parser that converts user input into executable Command objects.
 */
public class CommandParser {

    private final Ui ui;

    /**
     * Creates a CommandParser with the given UI.
     *
     * @param ui The UI used to pass to created commands.
     */
    public CommandParser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses user input and returns the corresponding Command.
     *
     * @param input raw user input
     * @return Command to execute
     * @throws ZackException if command is unknown or invalid
     */
    public Command parse(String input) throws ZackException {
        if (input == null || input.trim().isEmpty()) {
            throw new ZackException("OOPS!!! Please enter a command.");
        }

        String userInput = input.trim();
        String commandWord = userInput.split("\\s+", 2)[0].toLowerCase();

        switch (commandWord) {
        case "bye":
            return new ExitCommand(ui);

        case "list":
            return new ListCommand(ui);

        case "todo":
            return new TodoCommand(userInput, ui);

        case "mark":
            return new MarkCommand(userInput, ui);

        case "unmark":
            return new UnmarkCommand(userInput, ui);

        case "delete":
            return new DeleteCommand(userInput, ui);

        case "deadline":
            return new DeadlineCommand(userInput, ui);

        case "event":
            return new EventCommand(userInput, ui);

        case "find":
            return new FindCommand(userInput, ui);

        case "sort":
            return new SortCommand(ui);

        default:
            throw new ZackException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
