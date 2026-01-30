package tom.command;

import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
