package tom.command;

import tom.storage.Storage;
import tom.task.Task;
import tom.task.TaskList;
import tom.ui.Ui;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Creates an add command for the provided task.
     *
     * @param task Task to add.
     */
    public AddCommand(Task task) {
        assert task != null : "task should not be null";
        this.task = task;
    }

    /**
     * Adds the task to the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "tasks should not be null";
        return tasks.add(task);
    }

    /**
     * Returns whether this command exits the application.
     *
     * @return False because adding a task does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
