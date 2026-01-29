package tom.ui;
/**
 * Provides console output messages for the application.
 */
public class Ui {
    /** Divider line used to frame console output. */
    public String border = "____________________________________________________________";
    
    /**
     * Shows the welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println(border);
        System.out.println("Hello! I'm Tom");
        System.out.println("What can I do for you?");
        System.out.println(border);
    }

    /**
     * Shows the exit message.
     */
    public void showExitMessage() {
        System.out.println(border);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(border);
    }

    /**
     * Shows an error message.
     *
     * @param message Error message to display.
     */
    public void showErrorMessage(String message) {
        System.out.println(border);
        System.out.println("OOPS!!! " + message);
        System.out.println(border);
    }

    /**
     * Shows the loading error message.
     */
    public void showLoadingError() {
        System.out.println(border);
        System.out.println("OOPS!!! There was an error loading your tasks.");
        System.out.println(border);
    }

    /**
     * Shows the output divider line.
     */
    public void border() {
        System.out.println(border);
    }

    /**
     * Shows the mark-done confirmation message.
     *
     * @param item Task line to display.
     */
    public void showMarkDone(String item) {
        System.out.println(border);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + item);
        System.out.println(border);
    }

    /**
     * Shows the invalid task number message.
     */
    public void showInvalidTaskNumber() {
        System.out.println(border);
        System.out.println("Invalid task number.");
        System.out.println(border); 
    }

    /**
     * Shows the add-task confirmation message.
     *
     * @param size Updated number of tasks in the list.
     */
    public void showAddTask(int size) {
        System.out.println(border);
        System.out.println("Got it. I've added this task:");
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(border);
    }

    /**
     * Shows the unmark confirmation message.
     *
     * @param item Task line to display.
     */
    public void showUnmarkDone(String item) {
        System.out.println(border);
        System.out.println("Nice! I've marked this task as undone:");
        System.out.println("  " + item);
        System.out.println(border);
    }

    /**
     * Shows the delete-task confirmation message.
     *
     * @param item Task line to display.
     * @param size Updated number of tasks in the list.
     */
    public void showDeleteTask(String item, int size) {
        System.out.println(border);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + item);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(border);
    }


}
