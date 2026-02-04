package tom.ui;

import java.util.List;

import tom.task.Task;

/**
 * Provides console output messages for the application.
 */
public class Ui {
    /** Divider line used to frame console output. */
    private static final String BORDER = "";

    /**
     * Returns the welcome message.
     */
    public String showWelcomeMessage() {
        return BORDER + "\nHello! I'm Tom\nWhat can I do for you?\n" + BORDER;
    }

    /**
     * Shows the exit message.
     */
    public String showExitMessage() {
        return BORDER + "\nBye. Hope to see you again soon!\n" + BORDER;
    }

    /**
     * Shows an error message.
     *
     * @param message Error message to display.
     */
    public String showErrorMessage(String message) {
        return BORDER + "\nOOPS!!! " + message + "\n" + BORDER;
    }

    /**
     * Shows the loading error message.
     */
    public String showLoadingError() {
        return BORDER + "\nOOPS!!! There was an error loading your tasks.\n" + BORDER;
    }

    /**
     * Shows the output divider line.
     */
    public String border() {
        return BORDER;
    }

    /**
     * Shows the mark-done confirmation message.
     *
     * @param item Task line to display.
     */
    public String showMarkDone(String item) {
        return BORDER + "\nNice! I've marked this task as done:\n  " + item + "\n" + BORDER;
    }

    /**
     * Shows the invalid task number message.
     */
    public String showInvalidTaskNumber() {
        return BORDER + "\nInvalid task number.\n" + BORDER;
    }

    /**
     * Shows the add-task confirmation message.
     *
     * @param size Updated number of tasks in the list.
     */
    public String showAddTask(int size) {
        return BORDER + "\nGot it. I've added this task:\nNow you have " + size + " tasks in the list.\n" + BORDER;
    }

    /**
     * Shows the unmark confirmation message.
     *
     * @param item Task line to display.
     */
    public String showUnmarkDone(String item) {
        return BORDER + "\nNice! I've marked this task as undone:\n  " + item + "\n" + BORDER;
    }

    /**
     * Shows the delete-task confirmation message.
     *
     * @param item Task line to display.
     * @param size Updated number of tasks in the list.
     */
    public String showDeleteTask(String item, int size) {
        return BORDER + "\nNoted. I've removed this task:\n  " + item + "\nNow you have " + size + " tasks in the list.\n" + BORDER;
    }

    /**
     * Shows the found-tasks message.
     *
     * @param count Number of matching tasks.
     * @param keyword Keyword used for filtering.
     * @param foundTasks Tasks that match the keyword.
     */
    public String showFoundTasks(int count, String keyword, List<Task> foundTasks) {
        StringBuilder output = new StringBuilder();
        output.append(BORDER).append("\nFound ").append(count).append(" matching tasks in your list:\n");
        output.append("Here are the tasks matching \"").append(keyword).append("\":\n");
        for (Task task : foundTasks) {
            output.append("  ").append(task).append("\n");
        }
        output.append(BORDER);
        return output.toString();
    }
}
