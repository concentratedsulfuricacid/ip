package tom.command;
import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;

public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(taskNumber);
    }

    @Override
    public boolean isExit() {
        return false;
    }
    
}
