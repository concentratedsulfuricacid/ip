package tom;

import org.junit.jupiter.api.Test;
import tom.command.Command;
import tom.command.DeleteCommand;
import tom.command.ExitCommand;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Verifies parser command handling.
 */
public class ParserTest {

    /**
     * Verifies that parsing "bye" returns an exit command.
     */
    @Test
    void parse_bye_returnsExitCommand() throws Exception {
        Command c = Parser.parse("bye");
        assertTrue(c instanceof ExitCommand);
    }

    /**
     * Verifies that parsing a valid delete command returns a delete command.
     */
    @Test
    void parse_delete_valid_returnsDeleteCommand() throws Exception {
        Command c = Parser.parse("delete 2");
        assertTrue(c instanceof DeleteCommand);
    }

    /**
     * Verifies that parsing a delete command with no number throws.
     */
    @Test
    void parse_delete_missingNumber_throws() {
        assertThrows(TomException.class, () -> Parser.parse("delete"));
    }

    /**
     * Verifies that parsing a delete command with a non-integer throws.
     */
    @Test
    void parse_delete_nonInteger_throws() {
        assertThrows(TomException.class, () -> Parser.parse("delete abc"));
    }

    /**
     * Verifies that parsing a todo with no description throws.
     */
    @Test
    void parse_todo_emptyDescription_throws() {
        assertThrows(TomException.class, () -> Parser.parse("todo"));
    }

    /**
     * Verifies that parsing a deadline with no /by clause throws.
     */
    @Test
    void parse_deadline_missingBy_throws() {
        assertThrows(TomException.class, () -> Parser.parse("deadline submit"));
    }
}
