package zack.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void newTodo_initialState_notDone() {
        Todo todo = new Todo("read book");

        assertFalse(todo.isDone());
        assertEquals("[  ]", todo.getStatus());
    }

    @Test
    public void markDone_thenUnmark_statusChangesCorrectly() {
        Todo todo = new Todo("read book");

        todo.markDone();
        assertTrue(todo.isDone());
        assertEquals("[X]", todo.getStatus());

        todo.markNotDone();
        assertFalse(todo.isDone());
        assertEquals("[  ]", todo.getStatus());
    }

    @Test
    public void getDescription_returnsOriginalDescription() {
        Todo todo = new Todo("finish homework");
        assertEquals("finish homework", todo.getDescription());
    }

    @Test
    public void todo_hasNoDate_andSortDateIsNull() {
        Todo todo = new Todo("no date task");

        assertFalse(todo.hasDate());
        assertNull(todo.getSortDate());
    }

    @Test
    public void deadline_hasDate_andReturnsDueDate() {
        LocalDate date = LocalDate.of(2026, 3, 10);
        Deadline deadline = new Deadline("submit report", date);

        assertTrue(deadline.hasDate());
        assertEquals(date, deadline.getSortDate());
    }

    @Test
    public void event_hasDate_andReturnsStartDate() {
        LocalDate start = LocalDate.of(2026, 2, 20);
        LocalDate end = LocalDate.of(2026, 2, 21);
        Event event = new Event("conference", start, end);

        assertTrue(event.hasDate());
        assertEquals(end, event.getSortDate());
    }
}
//I use chatgpt to help me come up some tested cases
