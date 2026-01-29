package tom;

import org.junit.jupiter.api.Test;
import tom.command.Command;
import tom.command.DeleteCommand;
import tom.command.ExitCommand;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    void parse_bye_returnsExitCommand() throws Exception {
        Command c = Parser.parse("bye");
        assertTrue(c instanceof ExitCommand);
    }

    @Test
    void parse_delete_valid_returnsDeleteCommand() throws Exception {
        Command c = Parser.parse("delete 2");
        assertTrue(c instanceof DeleteCommand);
    }

    @Test
    void parse_delete_missingNumber_throws() {
        assertThrows(TomException.class, () -> Parser.parse("delete"));
    }

    @Test
    void parse_delete_nonInteger_throws() {
        assertThrows(TomException.class, () -> Parser.parse("delete abc"));
    }

    @Test
    void parse_todo_emptyDescription_throws() {
        assertThrows(TomException.class, () -> Parser.parse("todo"));
    }

    @Test
    void parse_deadline_missingBy_throws() {
        assertThrows(TomException.class, () -> Parser.parse("deadline submit"));
    }
}