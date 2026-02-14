package tom.command;

import java.util.List;

import tom.storage.Storage;
import tom.task.Task;
import tom.task.TaskList;
import tom.ui.Ui;

/**
 * Finds tasks containing a specific keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Creates a find command for the provided keyword.
     *
     * @param keyword Keyword to search for.
     */
    public FindCommand(String keyword) {
        assert keyword != null : "keyword should not be null";
        this.keyword = keyword;
    }

    /**
     * Finds tasks that contain the keyword and reports them through the UI.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "tasks should not be null";
        assert ui != null : "ui should not be null";
        List<Task> found = tasks.find(keyword);
        int count = found.size();
        return ui.showFoundTasks(count, keyword, found);
    }

    /**
     * Returns whether this command exits the application.
     *
     * @return False because finding tasks does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
