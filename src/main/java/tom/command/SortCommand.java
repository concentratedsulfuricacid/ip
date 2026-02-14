package tom.command;

import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;

/**
 * Sorts tasks by description.
 */
public class SortCommand extends Command {

    /**
     * Sorts tasks in the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "tasks should not be null";
        return tasks.sortByDescription();
    }

    /**
     * Returns whether this command exits the application.
     *
     * @return False because sorting does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
