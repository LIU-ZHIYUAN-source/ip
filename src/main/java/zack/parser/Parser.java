package zack.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import zack.exception.ZackException;
import zack.task.Deadline;
import zack.task.Event;
import zack.task.Todo;

/**
 * Parses user input strings into tasks and indexes.
 */
public class Parser {
    /**
     * Parses the index from the given command input.
     *
     * @param input   Full user input string.
     * @param keyword Command keyword.
     * @return Zero-based index.
     * @throws ZackException If the index is missing or invalid.
     */
    public static int parseIndex(String input, String keyword) throws ZackException {
        try {
            String num = input.substring(keyword.length()).trim();
            int index = Integer.parseInt(num) - 1;
            if (index < 0) {
                throw new ZackException("OOPS!!! Index must be a positive number.");
            }
            return index;
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            throw new ZackException("OOPS!!! Please provide a valid index.");
        }
    }

    /**
     * Parses a todo command.
     *
     * @param input Full user string.
     * @return A Todo task.
     * @throws ZackException If the input is invalid.
     */
    public static Todo parseTodo(String input) throws ZackException {
        if (input.equals("todo")) {
            throw new ZackException("OOPS!!! The description of a todo cannot be empty.");
        }
        String desc = input.substring(5).trim();
        if (desc.isEmpty()) {
            throw new ZackException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(desc);
    }

    /**
     * Parses a deadline command.
     *
     * @param input   Full user input string.
     * @param dateFmt Date format used to parse the deadline date.
     * @return A Deadline task.
     * @throws ZackException If the input is invalid.
     */
    public static Deadline parseDeadline(String input, DateTimeFormatter dateFmt) throws ZackException {
        String rest = input.substring(9);
        String[] parts = rest.split("/by", 2);
        if (parts.length < 2) {
            throw new ZackException("OOPS!!! Usage: deadline <description> /by yyyy-mm-dd");
        }

        String desc = parts[0].trim();
        String byStr = parts[1].trim();

        if (desc.isEmpty() || byStr.isEmpty()) {
            throw new ZackException("OOPS!!! Usage: deadline <description> /by yyyy-mm-dd");
        }

        try {
            LocalDate by = LocalDate.parse(byStr, dateFmt);
            return new Deadline(desc, by);
        } catch (DateTimeParseException e) {
            throw new ZackException("OOPS!!! Date must be yyyy-mm-dd");
        }
    }

    /**
     * Parses an event command.
     *
     * @param input   Full user input string.
     * @param dateFmt Date format used to parse the event dates.
     * @return An Event task.
     * @throws ZackException If the input is invalid.
     */
    public static Event parseEvent(String input, DateTimeFormatter dateFmt) throws ZackException {
        String rest = input.substring(6);
        String[] firstSplit = rest.split("/from", 2);
        if (firstSplit.length < 2) {
            throw new ZackException("OOPS!!! Usage: event <description> /from yyyy-mm-dd /to yyyy-mm-dd");
        }

        String desc = firstSplit[0].trim();
        String[] secondSplit = firstSplit[1].split("/to", 2);
        if (secondSplit.length < 2) {
            throw new ZackException("OOPS!!! Usage: event <description> /from yyyy-mm-dd /to yyyy-mm-dd");
        }

        String fromStr = secondSplit[0].trim();
        String toStr = secondSplit[1].trim();

        if (desc.isEmpty() || fromStr.isEmpty() || toStr.isEmpty()) {
            throw new ZackException("OOPS!!! Usage: event <description> /from yyyy-mm-dd /to yyyy-mm-dd");
        }

        try {
            LocalDate from = LocalDate.parse(fromStr, dateFmt);
            LocalDate to = LocalDate.parse(toStr, dateFmt);
            return new Event(desc, from, to);
        } catch (DateTimeParseException e) {
            throw new ZackException("OOPS!!! Date must be yyyy-mm-dd");
        }
    }
}
