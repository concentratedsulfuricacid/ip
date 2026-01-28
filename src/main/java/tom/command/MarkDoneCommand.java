package tom.command;
import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;

public class MarkDoneCommand extends Command {
    private int taskNumber;

    public MarkDoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
         tasks.markDone(taskNumber);
    }

    @Override
    public boolean isExit() {
        return false;
    }
    
}
