package zack.parser;

import org.junit.jupiter.api.Test;
import zack.exception.ZackException;
import zack.task.Todo;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parseTodo_validInput_success() throws ZackException {
        Todo todo = Parser.parseTodo("todo read book");

        assertEquals("read book", todo.getDescription());
        assertFalse(todo.isDone());
        assertEquals("[T]", todo.getType());
    }

    @Test
    public void parseTodo_missingDescription_exceptionThrown() {
        assertThrows(ZackException.class, () -> Parser.parseTodo("todo"));
    }

    @Test
    public void parseTodo_blankDescription_exceptionThrown() {
        assertThrows(ZackException.class, () -> Parser.parseTodo("todo   "));
    }
}