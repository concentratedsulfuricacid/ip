import java.util.Scanner;
public class Tom {
    static Scanner scanner = new Scanner(System.in);
    static String border = "____________________________________________________________";
    
    public static void echo(String input){
        System.out.println(border);
        System.out.println(input);
        System.out.println(border);
    }
    static Task[] items = new Task[100];

    public static void add(String items) {
        for (int i = 0; i < Tom.items.length; i++) {
            if (Tom.items[i] == null) {
                Tom.items[i] = new Task(items);

                System.out.println(border);
                System.out.println("added: " + items);
                System.out.println(border);
                break;
            }
        }
    }

    public static void list_items() {
        System.out.println(border);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.println((i + 1) + ". " + items[i]);
            }
        }
        System.out.println(border);
    } 

    public static void markDone(int taskNumber) {
        int index = taskNumber - 1;
        if (index >= 0 && index < items.length && items[index] != null) {
            items[index].markAsDone();
            System.out.println(border);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + items[index]);
            System.out.println(border);
        } else {
            System.out.println(border);
            System.out.println("Invalid task number.");
            System.out.println(border); 
        }
    } 
    
    public static void markUndone(int taskNumber) {
        int index = taskNumber - 1;
        if (index >= 0 && index < items.length && items[index] != null) {
            items[index].unmarkAsDone();
            System.out.println(border);
            System.out.println("Nice! I've marked this task as undone:");
            System.out.println("  " + items[index]);
            System.out.println(border);
        } else {
            System.out.println(border);
            System.out.println("Invalid task number.");
            System.out.println(border); 
        }
    }


    public static void main(String[] args) {
        String greeting = "Hello! I'm Tom! \n"
                        + "What can I do for you?";
        String exit = "Bye. Hope to see you again soon!";

        
        System.out.println(border);
        System.out.println(greeting);
        System.out.println(border);

        // Conversation logic 
        String message = scanner.nextLine();
        while (!message.equals("bye")) { 
            if (message.equals("list")) {
                list_items();
            } else if (message.startsWith("mark")){
                int taskNumber = Integer.parseInt(message.split(" ")[1]);
                markDone(taskNumber);
            } else if (message.startsWith("unmark")){
                int taskNumber = Integer.parseInt(message.split(" ")[1]);
                markUndone(taskNumber);
            } else {
                add(message);
            }
            message = scanner.nextLine();
        }
        
        System.out.println(border);
        System.out.println(exit);
        System.out.println(border);

    }
}
