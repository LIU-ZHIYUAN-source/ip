package zack.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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

