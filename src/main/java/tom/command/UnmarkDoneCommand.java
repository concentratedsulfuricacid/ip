package tom.command;
import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;

/**
 * Marks a task as not done in the task list.
 */
public class UnmarkDoneCommand extends Command {
    private int taskNumber;

    /**
     * Creates an unmark command for the provided task number.
     *
     * @param taskNumber Task number to unmark.
     */
    public UnmarkDoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the specified task as not done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markUndone(taskNumber);
    }
    
    /**
     * Returns whether this command exits the application.
     *
     * @return False because unmarking a task does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
