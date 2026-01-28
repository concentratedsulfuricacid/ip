public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
    }

    @Override
    boolean isExit() {
        return false;
    }

}