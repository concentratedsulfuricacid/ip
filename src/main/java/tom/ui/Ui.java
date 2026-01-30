package tom.ui;

import tom.task.Task;
import java.util.List;
public class Ui {
    private static final String BORDER = "____________________________________________________________";

    public void showWelcomeMessage() {
        System.out.println(BORDER);
        System.out.println("Hello! I'm Tom");
        System.out.println("What can I do for you?");
        System.out.println(BORDER);
    }

    public void showExitMessage() {
        System.out.println(BORDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BORDER);
    }

    public void showErrorMessage(String message) {
        System.out.println(BORDER);
        System.out.println("OOPS!!! " + message);
        System.out.println(BORDER);
    }

    public void showLoadingError() {
        System.out.println(BORDER);
        System.out.println("OOPS!!! There was an error loading your tasks.");
        System.out.println(BORDER);
    }

    public void border() {
        System.out.println(BORDER);
    }

    public void showMarkDone(String item) {
        System.out.println(BORDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + item);
        System.out.println(BORDER);
    }

    public void showInvalidTaskNumber() {
        System.out.println(BORDER);
        System.out.println("Invalid task number.");
        System.out.println(BORDER);
    }

    public void showAddTask(int size) {
        System.out.println(BORDER);
        System.out.println("Got it. I've added this task:");
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(BORDER);
    }

    public void showUnmarkDone(String item) {
        System.out.println(BORDER);
        System.out.println("Nice! I've marked this task as undone:");
        System.out.println("  " + item);
        System.out.println(BORDER);
    }

    public void showDeleteTask(String item, int size) {
        System.out.println(BORDER);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + item);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(BORDER);
    }

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
