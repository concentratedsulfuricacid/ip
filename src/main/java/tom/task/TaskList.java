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
    public String add(Task task) {
        items.add(task);
        Storage.updateStorage(items);
        return ui.showAddTask(items.size());
    }

    /**
     * Lists all tasks to the console.
     */
    public String listItems() {
        StringBuilder output = new StringBuilder();
        output.append(ui.border()).append("\nHere are the tasks in your list:\n");
        for (int i = 0; i < items.size(); i++) {
            output.append((i + 1)).append(". ").append(items.get(i)).append("\n");
        }
        return output.toString();
    }
        

    /**
     * Marks the specified task as done.
     *
     * @param taskNumber One-based task number.
     */
    public String markDone(int taskNumber) {
        int index = taskNumber - 1;
        if (index >= 0 && index < items.size() && items.get(index) != null) {
            items.get(index).markAsDone();
            Storage.updateStorage(items);
            return ui.showMarkDone(items.get(index).toString());
        } else {
            return ui.showInvalidTaskNumber();
        }
    }

    /**
     * Marks the specified task as not done.
     *
     * @param taskNumber One-based task number.
     */
    public String markUndone(int taskNumber) {
        int index = taskNumber - 1;
        if (index >= 0 && index < items.size() && items.get(index) != null) {
            items.get(index).unmarkAsDone();
            Storage.updateStorage(items);
            return ui.showUnmarkDone(items.get(index).toString());
        } else {
            return ui.showInvalidTaskNumber();
        }
    }

    /**
     * Deletes the specified task from the list.
     *
     * @param taskNumber One-based task number.
     */
    public String delete(int taskNumber) {
        int index = taskNumber - 1;
        if (index >= 0 && index < items.size() && items.get(index) != null) {
            Task removedTask = items.remove(index);
            Storage.updateStorage(items);
            return ui.showDeleteTask(removedTask.toString(), items.size());
        } else {
            return ui.showInvalidTaskNumber();
        }
    }

    /**
     * Returns tasks whose descriptions contain the provided keyword.
     *
     * @param keyword Keyword to match.
     * @return List of matching tasks.
     */
    public List<Task> find(String keyword) {
        String keywordLowercase = keyword.toLowerCase();
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : items) {
            if (task.getDescription().toLowerCase().contains(keywordLowercase)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
