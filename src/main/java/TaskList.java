import java.util.ArrayList;
import java.util.List;
public class TaskList {
    private Ui ui = new Ui();
    private List<Task> items = new ArrayList<>();
    
    public TaskList(List<Task> tasks) {
        this.items = tasks;
    }

    public int size() {
        return items.size();
    }

    public void add(Task task) {
        items.add(task);
        Storage.updateStorage(items);
        ui.showAddTask(items.size());
    } 

    public void list_items() {
        ui.border();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
            }
        ui.border();
    }

    public void markDone(int taskNumber) {
        int index = taskNumber - 1;
        if (index >= 0 && index < items.size() && items.get(index) != null) {
            items.get(index).markAsDone();
            Storage.updateStorage(items);
            ui.showMarkDone(items.get(index).toString());
        } else {
            ui.showInvalidTaskNumber();
        }
    }

    public  void markUndone(int taskNumber) {
        int index = taskNumber - 1;
        if (index >= 0 && index < items.size() && items.get(index) != null) {
            items.get(index).unmarkAsDone();
            Storage.updateStorage(items);
            ui.showUnmarkDone(items.get(index).toString());
        } else {
            ui.showInvalidTaskNumber();
        }
    }

    public void delete(int taskNumber) {
        int index = taskNumber - 1;
        if (index >= 0 && index < items.size() && items.get(index) != null) {
            Task removedTask = items.remove(index);
            Storage.updateStorage(items);
            ui.showDeleteTask(removedTask.toString(), items.size());
        } else {
            ui.showInvalidTaskNumber();
        }
    }

    








}
