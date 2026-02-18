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

/**
 * Parses full user input into Command objects.
 */
public class CommandParser {

    /**
     * Parses user input and returns the corresponding Command.
     *
     * @param input raw user input
     * @return Command to execute
     * @throws ZackException if command is unknown or invalid
     */
    public static Command parse(String input) throws ZackException {
        if (input == null || input.trim().isEmpty()) {
            throw new ZackException("OOPS!!! Please enter a command.");
        }

        String userInput = input.trim();
        String commandWord = userInput.split("\\s+", 2)[0].toLowerCase();

        switch (commandWord) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "todo":
            return new TodoCommand(userInput);

        case "mark":
            return new MarkCommand(userInput);

        case "unmark":
            return new UnmarkCommand(userInput);

        case "delete":
            return new DeleteCommand(userInput);

        case "deadline":
            return new DeadlineCommand(userInput);

        case "event":
            return new EventCommand(userInput);

        case "find":
            return new FindCommand(userInput);

        case "sort":
            return new SortCommand();

        default:
            throw new ZackException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
