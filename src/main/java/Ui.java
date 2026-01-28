public class Ui {
    public String border = "____________________________________________________________";
    
    public void showWelcomeMessage() {
        System.out.println(border);
        System.out.println("Hello! I'm Tom");
        System.out.println("What can I do for you?");
        System.out.println(border);
    }

    public void showExitMessage() {
        System.out.println(border);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(border);
    }

    public void showErrorMessage(String message) {
        System.out.println(border);
        System.out.println("OOPS!!! " + message);
        System.out.println(border);
    }

    public void showLoadingError() {
        System.out.println(border);
        System.out.println("OOPS!!! There was an error loading your tasks.");
        System.out.println(border);
    }

    public void border() {
        System.out.println(border);
    }

    public void showMarkDone(String item) {
        System.out.println(border);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + item);
        System.out.println(border);
    }

    public void showInvalidTaskNumber() {
        System.out.println(border);
        System.out.println("Invalid task number.");
        System.out.println(border); 
    }

    public void showAddTask(int size) {
        System.out.println(border);
        System.out.println("Got it. I've added this task:");
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(border);
    }

    public void showUnmarkDone(String item) {
        System.out.println(border);
        System.out.println("Nice! I've marked this task as undone:");
        System.out.println("  " + item);
        System.out.println(border);
    }

    public void showDeleteTask(String item, int size) {
        System.out.println(border);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + item);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(border);
    }


}
