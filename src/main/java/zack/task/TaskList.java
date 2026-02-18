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

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        String key = keyword.trim().toLowerCase();

        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(key)) {
                result.add(t);
            }
        }
        return result;
    }

    public void sortByDate() {
        tasks.sort((a, b) -> {
            if (a.hasDate() && b.hasDate()) {
                return a.getSortDate().compareTo(b.getSortDate());
            }
            if (a.hasDate()) {
                return -1;
            }
            if (b.hasDate()) {
                return 1;
            }
            return 0;
        });
    }
}
