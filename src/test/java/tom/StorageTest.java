package tom;

import org.junit.jupiter.api.Test;
import tom.storage.Storage;
import tom.task.Deadline;
import tom.task.Task;
import tom.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    @Test
    void encodeDecodeTodoRoundTrip() throws Exception {
        Todo t = new Todo("read book");
        t.markAsDone();

        String line = Storage.encode(t);
        Task decoded = Storage.decode(line);

        assertEquals(t.toString(), decoded.toString());
    }

    @Test
    void decodeDeadlineDoneFlagApplied() throws Exception {
        String line = "D | 1 | submit report | 2026-01-30";
        Task decoded = Storage.decode(line);

        assertTrue(decoded instanceof Deadline);
        assertTrue(decoded.toString().contains("[X]"));
    }

    @Test
    void decodeInvalidLineThrows() {
        assertThrows(TomException.class, () -> Storage.decode("nonsense"));
    }

    @Test
    void decodeMissingPartsThrows() {
        assertThrows(TomException.class, () -> Storage.decode("T | 0"));
    }
}
