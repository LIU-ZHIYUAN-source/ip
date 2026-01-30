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

public class Storage {
    private final Path dataDir;
    private final Path dataFile;
    private final DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

    public Storage(Path dataDir, Path dataFile) {
        this.dataDir = dataDir;
        this.dataFile = dataFile;
    }

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

