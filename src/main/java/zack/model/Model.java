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
 * Represents the core application logic of Zack.
 * Manages task operations on the task list and persists changes to storage.
 */
public class Model {

    private static final DateTimeFormatter DATE_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructs a Model with the given task list and storage.
     *
     * @param taskList The task list to manage.
     * @param storage  The storage used for persistence.
     */
    public Model(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Returns the underlying task list.
     *
     * @return The task list.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Returns the number of tasks currently stored.
     *
     * @return The task count.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Saves the current task list to storage.
     *
     * @throws ZackException If saving fails.
     */
    private void save() throws ZackException {
        storage.save(taskList.getTasks());
    }

    /**
     * Marks a task as done.
     *
     * @param fullInput The full user input.
     * @return The updated task.
     * @throws ZackException If the index is invalid or saving fails.
     */
    public Task mark(String fullInput) throws ZackException {
        int index = Parser.parseIndex(fullInput, "mark");
        Task task = taskList.get(index);
        task.markDone();
        save();
        return task;
    }

    /**
     * Marks a task as not done.
     *
     * @param fullInput The full user input.
     * @return The updated task.
     * @throws ZackException If the index is invalid or saving fails.
     */
    public Task unmark(String fullInput) throws ZackException {
        int index = Parser.parseIndex(fullInput, "unmark");
        Task task = taskList.get(index);
        task.markNotDone();
        save();
        return task;
    }

    /**
     * Deletes a task from the list.
     *
     * @param fullInput The full user input.
     * @return The removed task.
     * @throws ZackException If the index is invalid or saving fails.
     */
    public Task delete(String fullInput) throws ZackException {
        int index = Parser.parseIndex(fullInput, "delete");
        Task removed = taskList.remove(index);
        save();
        return removed;
    }

    /**
     * Adds a todo task.
     *
     * @param fullInput The full user input.
     * @return The created Todo task.
     * @throws ZackException If parsing or saving fails.
     */
    public Todo addTodo(String fullInput) throws ZackException {
        Todo todo = Parser.parseTodo(fullInput);
        taskList.add(todo);
        save();
        return todo;
    }

    /**
     * Adds a deadline task.
     *
     * @param fullInput The full user input.
     * @return The created Deadline task.
     * @throws ZackException If parsing or saving fails.
     */
    public Deadline addDeadline(String fullInput) throws ZackException {
        Deadline deadline = Parser.parseDeadline(fullInput, DATE_FMT);
        taskList.add(deadline);
        save();
        return deadline;
    }

    /**
     * Adds an event task.
     *
     * @param fullInput The full user input.
     * @return The created Event task.
     * @throws ZackException If parsing or saving fails.
     */
    public Event addEvent(String fullInput) throws ZackException {
        Event event = Parser.parseEvent(fullInput, DATE_FMT);
        taskList.add(event);
        save();
        return event;
    }

    /**
     * Finds tasks that contain the given keyword.
     *
     * @param fullInput The full user input.
     * @return A list of matching tasks.
     * @throws ZackException If the keyword is empty or no matching tasks are found.
     */
    public TaskList find(String fullInput) throws ZackException {
        String keyword = fullInput.substring(4).trim();

        if (keyword.isEmpty()) {
            throw new ZackException("OOPS!!! The keyword to find cannot be empty.");
        }

        ArrayList<Task> matches = taskList.find(keyword);

        if (matches.isEmpty()) {
            throw new ZackException("No matching tasks found.");
        }

        return new TaskList(matches);
    }


    /**
     * Sorts tasks by date and saves the updated list.
     *
     * @return A list of tasks sorted by time.
     * @throws ZackException If saving fails.
     */
    public TaskList sort() throws ZackException {
        taskList.sortByDate();
        save();
        return taskList;
    }
}
