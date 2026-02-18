package zack.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import zack.exception.ZackException;
import zack.parser.Parser;
import zack.storage.Storage;
import zack.task.Deadline;
import zack.task.Event;
import zack.task.Task;
import zack.task.TaskList;
import zack.task.Todo;

/**
 * Represents the application state.
 */
public class Model {
    private final TaskList taskList;
    private final Storage storage;

    private static final DateTimeFormatter DATE_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

    public Model(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public int size() {
        return taskList.size();
    }

    private void save() throws ZackException {
        storage.save(taskList.getTasks());
    }

    public Task mark(String fullInput) throws ZackException {
        int index = Parser.parseIndex(fullInput, "mark");
        Task t = taskList.get(index);
        t.markDone();
        save();
        return t;
    }

    public Task unmark(String fullInput) throws ZackException {
        int index = Parser.parseIndex(fullInput, "unmark");
        Task t = taskList.get(index);
        t.markNotDone();
        save();
        return t;
    }

    public Task delete(String fullInput) throws ZackException {
        int index = Parser.parseIndex(fullInput, "delete");
        Task removed = taskList.remove(index);
        save();
        return removed;
    }

    public Todo addTodo(String fullInput) throws ZackException {
        Todo todo = Parser.parseTodo(fullInput);
        taskList.add(todo);
        save();
        return todo;
    }

    public Deadline addDeadline(String fullInput) throws ZackException {
        Deadline d = Parser.parseDeadline(fullInput, DATE_FMT);
        taskList.add(d);
        save();
        return d;
    }

    public Event addEvent(String fullInput) throws ZackException {
        Event e = Parser.parseEvent(fullInput, DATE_FMT);
        taskList.add(e);
        save();
        return e;
    }

    public ArrayList<Task> find(String fullInput) throws ZackException {
        String keyword = fullInput.substring(5).trim();

        if (keyword.isEmpty()) {
            throw new ZackException("OOPS!!! The keyword to find cannot be empty.");
        }

        ArrayList<Task> matches = taskList.find(keyword);

        if (matches.isEmpty()) {
            throw new ZackException("No matching tasks found.");
        }

        return matches;
    }

    public void sort() throws ZackException {
        taskList.sortByDate();
        storage.save(taskList.getTasks());
    }
}

