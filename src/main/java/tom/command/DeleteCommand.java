package tom.command;

import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Creates a delete command for the provided task number.
     *
     * @param taskNumber Task number to delete.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes the specified task from the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.delete(taskNumber);
    }
    
    /**
     * Returns whether this command exits the application.
     *
     * @return False because deleting a task does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
