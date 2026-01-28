public class UnmarkDoneCommand extends Command {
    private int taskNumber;

    public UnmarkDoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markUndone(taskNumber);
    }
    
    @Override
    boolean isExit() {
        return false;
    }
}