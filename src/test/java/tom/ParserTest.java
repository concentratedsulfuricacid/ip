package tom;

import org.junit.jupiter.api.Test;
import tom.command.Command;
import tom.command.DeleteCommand;
import tom.command.ExitCommand;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Verifies parser command handling.
 */
public class ParserTest {

    /**
     * Verifies that parsing "bye" returns an exit command.
     */
    @Test
    void parseByeReturnsExitCommand() throws Exception {
        Command c = Parser.parse("bye");
        assertTrue(c instanceof ExitCommand);
    }

    /**
     * Verifies that parsing a valid delete command returns a delete command.
     */
    @Test
    void parseDeleteValidReturnsDeleteCommand() throws Exception {
        Command c = Parser.parse("delete 2");
        assertTrue(c instanceof DeleteCommand);
    }

    /**
     * Verifies that parsing a delete command with no number throws.
     */
    @Test
    void parseDeleteMissingNumberThrows() {
        assertThrows(TomException.class, () -> Parser.parse("delete"));
    }

    /**
     * Verifies that parsing a delete command with a non-integer throws.
     */
    @Test
    void parseDeleteNonIntegerThrows() {
        assertThrows(TomException.class, () -> Parser.parse("delete abc"));
    }

    /**
     * Verifies that parsing a todo with no description throws.
     */
    @Test
    void parseTodoEmptyDescriptionThrows() {
        assertThrows(TomException.class, () -> Parser.parse("todo"));
    }

    /**
     * Verifies that parsing a deadline with no /by clause throws.
     */
    @Test
    void parseDeadlineMissingByThrows() {
        assertThrows(TomException.class, () -> Parser.parse("deadline submit"));
    }
}
