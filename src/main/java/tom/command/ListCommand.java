package tom.command;

import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Lists the current tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.listItems();
    }

    /**
     * Returns whether this command exits the application.
     *
     * @return False because listing tasks does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
