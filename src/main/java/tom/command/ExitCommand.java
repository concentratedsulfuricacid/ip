package tom.command;

import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;

/**
 * Exits the application.
 */
public class ExitCommand extends Command {

    /**
     * Shows the exit message.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    /**
     * Returns whether this command exits the application.
     *
     * @return True because the exit command terminates the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
