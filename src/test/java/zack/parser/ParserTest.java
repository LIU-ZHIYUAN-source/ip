package zack.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import zack.exception.ZackException;
import zack.task.Todo;

public class ParserTest {

    @Test
    public void parseIndex_validIndex_returnsZeroBasedIndex() throws ZackException {
        assertEquals(0, Parser.parseIndex("mark 1", "mark"));
        assertEquals(4, Parser.parseIndex("delete 5", "delete"));
    }

    @Test
    public void parseIndex_invalidOrMissingIndex_throwsZackException() {
        assertThrows(ZackException.class, () -> Parser.parseIndex("mark", "mark"));
        assertThrows(ZackException.class, () -> Parser.parseIndex("mark abc", "mark"));
        assertThrows(ZackException.class, () -> Parser.parseIndex("mark 0", "mark"));
    }

    @Test
    public void parseTodo_emptyDescription_throwsZackException() {
        assertThrows(ZackException.class, () -> Parser.parseTodo("todo"));
        assertThrows(ZackException.class, () -> Parser.parseTodo("todo   "));
    }

    @Test
    public void parseTodo_validDescription_createsTodo() throws ZackException {
        Todo todo = Parser.parseTodo("todo read book");
        assertEquals("read book", todo.getDescription());
    }
}
//I use chatgpt to help me come up some tested cases
