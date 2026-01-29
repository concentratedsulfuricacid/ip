package tom.command;
import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;

/**
 * Defines a command that can be executed by the application.
 */
abstract public class Command {
    /**
     * Executes this command against the provided task list, UI, and storage.
     *
     * @param tasks Task list to operate on.
     * @param ui UI used to display feedback.
     * @param storage Storage used for persistence.
     */
    abstract public void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns whether this command signals application termination.
     *
     * @return True if the command exits the application.
     */
    abstract public boolean isExit();
}
