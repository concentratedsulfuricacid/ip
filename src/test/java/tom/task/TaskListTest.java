package tom.task;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Verifies task list search behavior.
 */
public class TaskListTest {
    /**
     * Verifies that search matches task descriptions case-insensitively.
     */
    @Test
    void findMatchesIgnoreCaseReturnsMatchingTasks() {
        List<Task> tasks = new ArrayList<>();
        Todo matching = new Todo("Read Book");
        tasks.add(matching);
        tasks.add(new Todo("buy milk"));
        TaskList taskList = new TaskList(tasks);

        List<Task> found = taskList.find("read");

        assertEquals(1, found.size());
        assertEquals(matching, found.get(0));
    }

    /**
     * Verifies that search returns an empty list when there are no matches.
     */
    @Test
    void findWithNoMatchesReturnsEmptyList() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        TaskList taskList = new TaskList(tasks);

        List<Task> found = taskList.find("meeting");

        assertTrue(found.isEmpty());
    }
}
