package tom.command;

import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listItems();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
