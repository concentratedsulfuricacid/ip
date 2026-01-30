package tom.command;

import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;
import java.util.List;
import tom.task.Task;

public class FindCommand extends Command {

    private String keyword;
    
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> found = tasks.find(keyword);
        int count = found.size();
        ui.showFoundTasks(count, keyword, found);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
