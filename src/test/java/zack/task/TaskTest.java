package zack.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void markDone_taskBecomesDone() {
        Task task = new Todo("read book");
        assertFalse(task.isDone());

        task.markDone();

        assertTrue(task.isDone());
        assertEquals("[X]", task.getStatus());
    }

    @Test
    public void markNotDone_afterMarkDone_taskBecomesNotDone() {
        Task task = new Todo("read book");
        task.markDone();

        task.markNotDone();

        assertFalse(task.isDone());
        assertEquals("[ ]", task.getStatus());
    }
}

