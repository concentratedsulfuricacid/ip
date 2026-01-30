package tom.ui;

import java.util.List;

import tom.task.Task;

/**
 * Provides console output messages for the application.
 */
public class Ui {
    /** Divider line used to frame console output. */
    private static final String BORDER = "____________________________________________________________";

    /**
     * Shows the welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println(BORDER);
        System.out.println("Hello! I'm Tom");
        System.out.println("What can I do for you?");
        System.out.println(BORDER);
    }

    /**
     * Shows the exit message.
     */
    public void showExitMessage() {
        System.out.println(BORDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BORDER);
    }

    /**
     * Shows an error message.
     *
     * @param message Error message to display.
     */
    public void showErrorMessage(String message) {
        System.out.println(BORDER);
        System.out.println("OOPS!!! " + message);
        System.out.println(BORDER);
    }

    /**
     * Shows the loading error message.
     */
    public void showLoadingError() {
        System.out.println(BORDER);
        System.out.println("OOPS!!! There was an error loading your tasks.");
        System.out.println(BORDER);
    }

    /**
     * Shows the output divider line.
     */
    public void border() {
        System.out.println(BORDER);
    }

    /**
     * Shows the mark-done confirmation message.
     *
     * @param item Task line to display.
     */
    public void showMarkDone(String item) {
        System.out.println(BORDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + item);
        System.out.println(BORDER);
    }

    /**
     * Shows the invalid task number message.
     */
    public void showInvalidTaskNumber() {
        System.out.println(BORDER);
        System.out.println("Invalid task number.");
        System.out.println(BORDER);
    }

    /**
     * Shows the add-task confirmation message.
     *
     * @param size Updated number of tasks in the list.
     */
    public void showAddTask(int size) {
        System.out.println(BORDER);
        System.out.println("Got it. I've added this task:");
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(BORDER);
    }

    /**
     * Shows the unmark confirmation message.
     *
     * @param item Task line to display.
     */
    public void showUnmarkDone(String item) {
        System.out.println(BORDER);
        System.out.println("Nice! I've marked this task as undone:");
        System.out.println("  " + item);
        System.out.println(BORDER);
    }

    /**
     * Shows the delete-task confirmation message.
     *
     * @param item Task line to display.
     * @param size Updated number of tasks in the list.
     */
    public void showDeleteTask(String item, int size) {
        System.out.println(BORDER);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + item);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(BORDER);
    }

    /**
     * Shows the found-tasks message.
     *
     * @param count Number of matching tasks.
     * @param keyword Keyword used for filtering.
     * @param foundTasks Tasks that match the keyword.
     */
    public void showFoundTasks(int count, String keyword, List<Task> foundTasks) {
        System.out.println(BORDER);
        System.out.println("Found " + count + " matching tasks in your list:");
        System.out.println("Here are the tasks matching \"" + keyword + "\":");
        for (Task task : foundTasks) {
            System.out.println("  " + task);
        }
        System.out.println(BORDER);
    }
}
