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

    public static void add(Task task) {
        for (int i = 0; i < Tom.items.length; i++) {
            if (Tom.items[i] == null) {
                Tom.items[i] = task;

                System.out.println(border);
                System.out.println("Got it. I've added this task: \n " + task);
                System.out.println("Now you have " + (i + 1) + " tasks in the list.");
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

    private static void parser(String message) throws TomException {
        if (message.startsWith("todo")) {
            String[] parts = message.split(" ", 2);
            String description = parts.length > 1 ? parts[1].trim() : "";
            if (description.isEmpty()) {
                throw new InvalidCommandException("The description of a todo cannot be empty.");
            }
            Todo todo = new Todo(description);
            add(todo);
            
        } else if (message.startsWith("deadline")) {
            if (!message.contains(" /by ")) {
                throw new InvalidCommandException("The deadline command must include a /by clause.");
            }
            String[] parts = message.substring(8).split(" /by ");
            String description = parts[0].trim();
            String by = parts[1].trim();
            if (description.isEmpty() || by.isEmpty()) {
                throw new InvalidCommandException("The description of a deadline cannot be empty.");
            }
            Deadline deadline = new Deadline(description, by);
            add(deadline);

        } else if (message.startsWith("event")) {
            String[] parts = message.substring(5).split(" /from | /to ");
            if (parts.length < 3) {
                throw new InvalidCommandException("The event command must include /from and /to clauses.");
            }
            String description = parts[0].trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            Event event = new Event(description, from, to);
            add(event);
        } else {
            throw new InvalidCommandException("Invalid command.");
        }
    }


    public static void main(String[] args) {
    String greeting = "Hello! I'm Tom! \n" + "What can I do for you?";
    String exit = "Bye. Hope to see you again soon!";

    System.out.println(border);
    System.out.println(greeting);
    System.out.println(border);

    String message = scanner.nextLine();
    while (!message.equals("bye")) {
        try {
            if (message.equals("list")) {
                list_items();
            } else if (message.startsWith("mark")) {
                String[] parts = message.trim().split("\\s+");
                if (parts.length < 2) {
                    System.out.println(border);
                    System.out.println("OOPS!!! Please enter a valid task number.");
                    System.out.println(border);
                    message = scanner.nextLine();
                    continue;
                }
                try {
                    int taskNumber = Integer.parseInt(parts[1]);
                    markDone(taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println(border);
                    System.out.println("OOPS!!! Please enter a valid task number.");
                    System.out.println(border);
                }
            } else if (message.startsWith("unmark")) {
                String[] parts = message.trim().split("\\s+");
                if (parts.length < 2) {
                    System.out.println(border);
                    System.out.println("OOPS!!! Please enter a valid task number.");
                    System.out.println(border);
                    message = scanner.nextLine();
                    continue;
                }
                try {
                    int taskNumber = Integer.parseInt(parts[1]);
                    markUndone(taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println(border);
                    System.out.println("OOPS!!! Please enter a valid task number.");
                    System.out.println(border);
                }
            } else {
                parser(message); 
            }
        } catch (TomException e) {
            System.out.println(border);
            System.out.println("OOPS!!! " + e.getMessage());
            System.out.println(border);
        } catch (NumberFormatException e) {
            System.out.println(border);
            System.out.println("OOPS!!! Please enter a valid task number.");
            System.out.println(border);
        }

        message = scanner.nextLine(); // important: always read next input even after errors
    }

    System.out.println(border);
    System.out.println(exit);
    System.out.println(border);
}
}
