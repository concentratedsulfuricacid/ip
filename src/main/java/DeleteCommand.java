public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(taskNumber);
    }

    @Override
    boolean isExit() {
        return false;
    }
    
}
