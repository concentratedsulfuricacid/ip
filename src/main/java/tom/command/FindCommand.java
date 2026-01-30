package tom.command;

import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;
import java.util.List;
import tom.task.Task;

/**
 * Finds tasks containing a specific keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Creates a find command for the provided keyword.
     *
     * @param keyword Keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Creates a find command for the provided keyword.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> found = tasks.find(keyword);
        int count = found.size();
        ui.showFoundTasks(count, keyword, found);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
