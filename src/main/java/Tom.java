import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Tom {
    static Scanner scanner = new Scanner(System.in);
    static String border = "____________________________________________________________";
    private enum CommandType {
        LIST, BYE, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, INVALID
    }

    private static CommandType getCommandType(String message) {
        String firstWord = message.trim().split("\\s+")[0].toLowerCase();
        return switch (firstWord) {
            case "list" -> CommandType.LIST;
            case "bye" -> CommandType.BYE;
            case "todo" -> CommandType.TODO;
            case "deadline" -> CommandType.DEADLINE;
            case "event" -> CommandType.EVENT;
            case "mark" -> CommandType.MARK;
            case "unmark" -> CommandType.UNMARK;
            case "delete" -> CommandType.DELETE;
            default -> CommandType.INVALID;
        };
    }

    private static int parseTaskNumber(String message) throws TomException {
        String[] parts = message.trim().split("\\s+");
        if (parts.length < 2) {
            throw new InvalidCommandException("Please enter a valid task number.");
        }
        try {
            return Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Please enter a valid task number.");
        }
    }

    
    public static void echo(String input){
        System.out.println(border);
        System.out.println(input);
        System.out.println(border);
    }
    
    static List<Task> items = new ArrayList<>();

    public static void loadTasks() {
        try {
            List<Task> loadedTasks = Storage.load();
            for (Task task : loadedTasks) {
                items.add(task);
            }
        } catch (TomException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
    

    public static void add(Task task) {
        items.add(task);
        Storage.updateStorage(items);
        System.out.println(border);
        System.out.println("Got it. I've added this task: \n " + task);
        System.out.println("Now you have " + (items.size()) + " tasks in the list.");
        System.out.println(border);
                
            
    }

    public static void list_items() {
        System.out.println(border);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
            }
        System.out.println(border);
    } 

    public static void markDone(int taskNumber) {
        int index = taskNumber - 1;
        if (index >= 0 && index < items.size() && items.get(index) != null) {
            items.get(index).markAsDone();
            Storage.updateStorage(items);
            System.out.println(border);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + items.get(index));
            System.out.println(border);
        } else {
            System.out.println(border);
            System.out.println("Invalid task number.");
            System.out.println(border); 
        }
    } 
    
    public static void markUndone(int taskNumber) {
        int index = taskNumber - 1;
        if (index >= 0 && index < items.size() && items.get(index) != null) {
            items.get(index).unmarkAsDone();
            Storage.updateStorage(items);
            System.out.println(border);
            System.out.println("Nice! I've marked this task as undone:");
            System.out.println("  " + items.get(index));
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

    public static void delete(int taskNumber) {
        int index = taskNumber - 1;
        if (index >= 0 && index < items.size() && items.get(index) != null) {
            Task removedTask = items.remove(index);
            Storage.updateStorage(items);
            System.out.println(border);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + items.size() + " tasks in the list.");
            System.out.println(border);
        } else {
            System.out.println(border);
            System.out.println("Invalid task number.");
            System.out.println(border); 
        }
    }




    public static void main(String[] args) {
    String greeting = "Hello! I'm Tom! \n" + "What can I do for you?";
    String exit = "Bye. Hope to see you again soon!";

    System.out.println(border);
    System.out.println(greeting);
    System.out.println(border);

    loadTasks();

    String message = scanner.nextLine();

    while (true) {
        message = message.trim();

        try {
            CommandType type = getCommandType(message);

            switch (type) {
                case BYE -> {
                    break; // breaks out of switch, but we want to exit loop
                }
                case LIST -> list_items();
                case MARK -> markDone(parseTaskNumber(message));
                case UNMARK -> markUndone(parseTaskNumber(message));
                case DELETE -> delete(parseTaskNumber(message));
                case TODO, DEADLINE, EVENT -> parser(message);
                case INVALID -> throw new InvalidCommandException("Invalid command.");
            }

            if (type == CommandType.BYE) {
                break; // exit the while loop
            }

        } catch (TomException e) {
            System.out.println(border);
            System.out.println("OOPS!!! " + e.getMessage());
            System.out.println(border);
        }


            message = scanner.nextLine(); 
        }

        System.out.println(border);
        System.out.println(exit);
        System.out.println(border);
    }
}
