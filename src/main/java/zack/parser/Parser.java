package zack.parser;

import zack.exception.ZackException;
import zack.task.Deadline;
import zack.task.Todo;
import zack.task.Event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static int parseIndex(String input, String keyword) throws ZackException {
        String num = input.substring(keyword.length()).trim();
        return Integer.parseInt(num) - 1;
    }

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
