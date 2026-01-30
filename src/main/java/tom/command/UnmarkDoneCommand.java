package tom.command;

import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;

public class UnmarkDoneCommand extends Command {
    private int taskNumber;

    public UnmarkDoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markUndone(taskNumber);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
