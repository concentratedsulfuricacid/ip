package tom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tom.storage.Storage;
import tom.task.Deadline;
import tom.task.Task;
import tom.task.Todo;

public class StorageTest {

    @Test
    void encodeDecode_todo_roundTrip() throws Exception {
        Todo t = new Todo("read book");
        t.markAsDone();

        String line = Storage.encode(t);
        Task decoded = Storage.decode(line);

        assertEquals(t.toString(), decoded.toString());
    }

    @Test
    void decode_deadline_doneFlagApplied() throws Exception {
        String line = "D | 1 | submit report | 2026-01-30";
        Task decoded = Storage.decode(line);

        assertTrue(decoded instanceof Deadline);
        assertTrue(decoded.toString().contains("[X]")); 
    }

    @Test
    void decode_invalidLine_throws() {
        assertThrows(TomException.class, () -> Storage.decode("nonsense"));
    }

    @Test
    void decode_missingParts_throws() {
        assertThrows(TomException.class, () -> Storage.decode("T | 0"));
    }
}