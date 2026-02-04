package tom.command;

import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;

/**
 * Marks a task as done in the task list.
 */
public class MarkDoneCommand extends Command {
    private int taskNumber;

    /**
     * Creates a mark-done command for the provided task number.
     *
     * @param taskNumber Task number to mark as done.
     */
    public MarkDoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the specified task as done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
         return tasks.markDone(taskNumber);
    }

    /**
     * Returns whether this command exits the application.
     *
     * @return False because marking a task does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
