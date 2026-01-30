package tom.task;

import java.util.ArrayList;
import java.util.List;

import tom.storage.Storage;
import tom.ui.Ui;

/**
 * Manages a list of tasks and related user interactions.
 */
public class TaskList {
    private final Ui ui;
    private final List<Task> items;
    /**
     * Creates a task list backed by the provided task collection.
     *
     * @param tasks Initial tasks.
     */

    public TaskList(List<Task> tasks) {
        this.ui = new Ui();
        this.items = new ArrayList<>(tasks);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return items.size();
    }

    /**
     * Adds a task to the list and persists the update.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        items.add(task);
        Storage.updateStorage(items);
        ui.showAddTask(items.size());
    }

    /**
     * Lists all tasks to the console.
     */
    public void listItems() {
        ui.border();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
        ui.border();
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskNumber One-based task number.
     */
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

    /**
     * Marks the specified task as not done.
     *
     * @param taskNumber One-based task number.
     */
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

    /**
     * Deletes the specified task from the list.
     *
     * @param taskNumber One-based task number.
     */
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

    public List<Task> find(String keyword) {
        String k = keyword.toLowerCase();
        List<Task> foundTasks = new ArrayList<Task>();
        for (Task task : items) {
            if (task.getDescription().toLowerCase().contains(k)) {
                foundTasks.add(task);   
            }
        }
        return foundTasks;
    }
}
