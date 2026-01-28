package tom.command;
import tom.storage.Storage;
import tom.task.Task;
import tom.task.TaskList;
import tom.ui.Ui;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}