package zack.task;

import java.util.ArrayList;

import zack.exception.ZackException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a task list using the given list of tasks.
     *
     * @param tasks List of tasks to be managed.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the given index.
     *
     * @param index Index of the task (0-based).
     * @return The task at the given index.
     * @throws ZackException If the index is out of bounds.
     */
    public Task get(int index) throws ZackException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZackException("OOPS!!! Index is out of bounds.");
        }
        return tasks.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the given index.
     *
     * @param index Index of the task to remove (0-based).
     * @return The removed task.
     * @throws ZackException If the index is out of bounds.
     */
    public Task remove(int index) throws ZackException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZackException("OOPS!!! Index is out of bounds.");
        }
        return tasks.remove(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds all tasks whose descriptions contain the given keyword (case-insensitive).
     *
     * @param keyword Keyword to search for.
     * @return A list of matching tasks.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        String key = keyword.trim().toLowerCase();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(key)) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Sorts tasks by date, placing dated tasks before undated tasks.
     * Dated tasks are ordered by their date in ascending order.
     */
    public void sortByDate() {
        tasks.sort((aTask, bTask) -> {
            if (aTask.hasDate() && bTask.hasDate()) {
                return aTask.getSortDate().compareTo(bTask.getSortDate());
            }
            if (aTask.hasDate()) {
                return -1;
            }
            if (bTask.hasDate()) {
                return 1;
            }
            return 0;
        });
    }
}
