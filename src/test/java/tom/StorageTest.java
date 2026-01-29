package tom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tom.storage.Storage;
import tom.task.Deadline;
import tom.task.Task;
import tom.task.Todo;

/**
 * Verifies storage encoding and decoding behavior.
 */
public class StorageTest {

    /**
     * Verifies that encoding and decoding a todo preserves its display string.
     */
    @Test
    void encodeDecode_todo_roundTrip() throws Exception {
        Todo t = new Todo("read book");
        t.markAsDone();

        String line = Storage.encode(t);
        Task decoded = Storage.decode(line);

        assertEquals(t.toString(), decoded.toString());
    }

    /**
     * Verifies that decoding a done deadline applies the done flag.
     */
    @Test
    void decode_deadline_doneFlagApplied() throws Exception {
        String line = "D | 1 | submit report | 2026-01-30";
        Task decoded = Storage.decode(line);

        assertTrue(decoded instanceof Deadline);
        assertTrue(decoded.toString().contains("[X]")); 
    }

    /**
     * Verifies that decoding an invalid line throws an exception.
     */
    @Test
    void decode_invalidLine_throws() {
        assertThrows(TomException.class, () -> Storage.decode("nonsense"));
    }

    /**
     * Verifies that decoding a line with missing parts throws an exception.
     */
    @Test
    void decode_missingParts_throws() {
        assertThrows(TomException.class, () -> Storage.decode("T | 0"));
    }
}
