package tom;

import org.junit.jupiter.api.Test;
import tom.command.Command;
import tom.command.DeleteCommand;
import tom.command.ExitCommand;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    void parseByeReturnsExitCommand() throws Exception {
        Command c = Parser.parse("bye");
        assertTrue(c instanceof ExitCommand);
    }

    @Test
    void parseDeleteValidReturnsDeleteCommand() throws Exception {
        Command c = Parser.parse("delete 2");
        assertTrue(c instanceof DeleteCommand);
    }

    @Test
    void parseDeleteMissingNumberThrows() {
        assertThrows(TomException.class, () -> Parser.parse("delete"));
    }

    @Test
    void parseDeleteNonIntegerThrows() {
        assertThrows(TomException.class, () -> Parser.parse("delete abc"));
    }

    @Test
    void parseTodoEmptyDescriptionThrows() {
        assertThrows(TomException.class, () -> Parser.parse("todo"));
    }

    @Test
    void parseDeadlineMissingByThrows() {
        assertThrows(TomException.class, () -> Parser.parse("deadline submit"));
    }
}
