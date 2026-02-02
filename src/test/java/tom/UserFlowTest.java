package tom;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import tom.command.Command;
import tom.storage.Storage;
import tom.task.Task;
import tom.task.TaskList;
import tom.task.Todo;
import tom.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Verifies a basic user flow across parsing, commands, and task updates.
 */
public class UserFlowTest {
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
     * Verifies adding, updating, deleting, and finding tasks in a single flow.
     */
    @Test
    void userFlowAddsUpdatesDeletesAndFindsTasks() throws Exception {
        TaskList taskList = new TaskList(new ArrayList<>());
        Storage storage = new Storage("data/tasks.txt");
        Ui ui = new Ui();

        Command todoCommand = Parser.parse("todo read book");
        todoCommand.execute(taskList, ui, storage);
        assertEquals(1, taskList.size());
        assertEquals(1, taskList.find("read").size());

        Command deadlineCommand = Parser.parse("deadline submit report /by 2026-02-01");
        deadlineCommand.execute(taskList, ui, storage);
        assertEquals(2, taskList.size());
        assertEquals(1, taskList.find("submit").size());

        Command eventCommand = Parser.parse("event project /from 2026-02-02 /to 2026-02-03");
        eventCommand.execute(taskList, ui, storage);
        assertEquals(3, taskList.size());
        assertEquals(1, taskList.find("project").size());

        Command markCommand = Parser.parse("mark 1");
        markCommand.execute(taskList, ui, storage);
        Task firstTask = taskList.find("read").get(0);
        assertTrue(firstTask.isDone());

        Command unmarkCommand = Parser.parse("unmark 1");
        unmarkCommand.execute(taskList, ui, storage);
        assertFalse(firstTask.isDone());

        Command deleteCommand = Parser.parse("delete 2");
        deleteCommand.execute(taskList, ui, storage);
        assertEquals(2, taskList.size());
        assertTrue(taskList.find("submit").isEmpty());

        Command findCommand = Parser.parse("find read");
        StubUi stubUi = new StubUi();
        findCommand.execute(taskList, stubUi, storage);
        assertEquals(1, stubUi.count);
        assertEquals("read", stubUi.keyword);
        assertEquals(1, stubUi.tasks.size());
        assertEquals(new Todo("read book"), stubUi.tasks.get(0));
    }
}
