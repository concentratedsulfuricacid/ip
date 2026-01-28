public class MarkDoneCommand extends Command {
    private int taskNumber;

    public MarkDoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
         tasks.markDone(taskNumber);
    }

    @Override
    boolean isExit() {
        return false;
    }
    
}
