package tom.command;
import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;

abstract public class Command {
    abstract public void execute(TaskList tasks, Ui ui, Storage storage);

    abstract public boolean isExit();
}