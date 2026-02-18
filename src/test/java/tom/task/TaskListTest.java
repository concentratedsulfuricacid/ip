package tom.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

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

    /**
     * Verifies that sorting orders tasks by description, ignoring case.
     */
    @Test
    void sortByDescriptionOrdersTasksIgnoreCase(@TempDir Path tempDir) throws Exception {
        String originalUserDir = System.getProperty("user.dir");
        System.setProperty("user.dir", tempDir.toString());
        Files.createDirectories(tempDir.resolve("data"));

        try {
            List<Task> tasks = new ArrayList<>();
            tasks.add(new Todo("Beta task"));
            tasks.add(new Todo("alpha task"));
            tasks.add(new Todo("Gamma task"));
            TaskList taskList = new TaskList(tasks);

            taskList.sortByDescription();

            String listing = taskList.listItems();
            int alphaIndex = listing.indexOf("alpha task");
            int betaIndex = listing.indexOf("Beta task");
            int gammaIndex = listing.indexOf("Gamma task");

            assertTrue(alphaIndex != -1 && betaIndex != -1 && gammaIndex != -1);
            assertTrue(alphaIndex < betaIndex);
            assertTrue(betaIndex < gammaIndex);
        } finally {
            System.setProperty("user.dir", originalUserDir);
        }
    }
}
