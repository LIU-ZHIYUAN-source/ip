package zack.storage;

import zack.exception.ZackException;
import zack.task.Deadline;
import zack.task.Task;
import zack.task.Event;
import zack.task.Todo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Handles saving tasks to disk and loading tasks from disk.
 */
public class Storage {
    private final Path dataDir;
    private final Path dataFile;
    private final DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

    /**
     * Constructs a storage component using the given directory and file path.
     *
     * @param dataDir Directory used to store the data file.
     * @param dataFile File path used to save and load tasks.
     */
    public Storage(Path dataDir, Path dataFile) {
        this.dataDir = dataDir;
        this.dataFile = dataFile;
    }

    /**
     * Saves the given tasks to disk.
     *
     * @param tasks List of tasks to be saved.
     * @throws ZackException If the tasks cannot be saved.
     */
    public void save(ArrayList<Task> tasks) throws ZackException {
        try {
            Files.createDirectories(dataDir);
            try (BufferedWriter writer = Files.newBufferedWriter(dataFile)) {
                for (Task t : tasks) {
                    writer.write(encodeTask(t));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new ZackException("OOPS!!! I couldn't save your tasks to disk.");
        }
    }

    /**
     * Loads tasks from disk.
     *
     * @return List of tasks loaded from disk.
     * @throws ZackException If the tasks cannot be loaded.
     */
    public ArrayList<Task> load() throws ZackException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (!Files.exists(dataFile)) {
                return tasks;
            }

            List<String> lines = Files.readAllLines(dataFile);
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                Task t = decodeTask(line);
                if (t != null) {
                    tasks.add(t);
                }
            }
        } catch (IOException e) {
            throw new ZackException("OOPS!!! I couldn't load your tasks from disk.");
        }

        return tasks;
    }

    /**
     * Encodes a task into a single-line string for saving.
     *
     * @param t Task to be encoded.
     * @return Encoded string representation of the task.
     */
    private String encodeTask(Task t) {
        String done = t.isDone() ? "1" : "0";

        if (t instanceof Todo) {
            return "T | " + done + " | " + t.getDescription();
        }
        if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return "D | " + done + " | " + d.getDescription() + " | " + d.getBy().format(dateFmt);
        }
        Event e = (Event) t;
        return "E | " + done + " | " + e.getDescription() + " | " + e.getFrom().format(dateFmt)
                + " | " + e.getTo().format(dateFmt);
    }

    /**
     * Decodes a single-line string into a task.
     *
     * @param line Encoded task string.
     * @return Decoded task, null if the line is invalid.
     */
    private Task decodeTask(String line) {
        String[] parts = line.split("\\s*\\|\\s*");

        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        boolean done = "1".equals(parts[1]);
        String desc = parts[2];

        Task t;

        switch (type) {
            case "T":
                t = new Todo(desc);
                break;
            case "D":
                if (parts.length < 4) {
                    return null;
                }
                LocalDate by = LocalDate.parse(parts[3], dateFmt);
                t = new Deadline(desc, by);
                break;
            case "E":
                if (parts.length < 5) {
                    return null;
                }
                LocalDate from = LocalDate.parse(parts[3], dateFmt);
                LocalDate to = LocalDate.parse(parts[4], dateFmt);
                t = new Event(desc, from, to);
                break;
            default:
                return null;
        }

        if (done) {
            t.markDone();
        }
        return t;
    }
}

