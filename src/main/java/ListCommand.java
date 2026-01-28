public class ListCommand extends Command {
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.list_items();
    }
    
    @Override
    boolean isExit() {
        return false;
    }
}
