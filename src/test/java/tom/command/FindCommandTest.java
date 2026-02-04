package tom.command;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import tom.storage.Storage;
import tom.task.Task;
import tom.task.TaskList;
import tom.task.Todo;
import tom.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Verifies find command behavior.
 */
public class FindCommandTest {
    /**
     * Captures UI output for assertions.
     */
    private static class StubUi extends Ui {
        private int count;
        private String keyword;
        private List<Task> tasks;

        @Override
        public String showFoundTasks(int count, String keyword, List<Task> foundTasks) {
            this.count = count;
            this.keyword = keyword;
            this.tasks = new ArrayList<>(foundTasks);
            return "Found " + count + " tasks with keyword '" + keyword + "'";
        }
    }

    /**
     * Verifies that executing the find command reports matching tasks to the UI.
     */
    @Test
    void executeReportsMatchesToUi() {
        List<Task> tasks = new ArrayList<>();
        Todo matching = new Todo("read book");
        tasks.add(matching);
        tasks.add(new Todo("buy milk"));
        TaskList taskList = new TaskList(tasks);
        FindCommand command = new FindCommand("read");
        StubUi ui = new StubUi();

        command.execute(taskList, ui, new Storage());

        assertEquals(1, ui.count);
        assertEquals("read", ui.keyword);
        assertEquals(1, ui.tasks.size());
        assertEquals(matching, ui.tasks.get(0));
    }
}
