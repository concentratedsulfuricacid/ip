import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Tom {
    private Storage storage;
    private Ui ui;
    private TaskList items;
    static Scanner scanner = new Scanner(System.in);


    public Tom(String Filepath) {
        this.storage = new Storage(Filepath);
        this.ui = new Ui();
        try {
            items = new TaskList(storage.load());
        } catch (TomException e) {
            ui.showLoadingError();
            items = new TaskList(new ArrayList<>());
        }
    }


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



    public void loadTasks() {
        try {
            List<Task> loadedTasks = storage.load();
            for (Task task : loadedTasks) {
                items.add(task);
            }
        } catch (TomException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
    

    private void parser(String message) throws TomException {
        if (message.startsWith("todo")) {
            String[] parts = message.split(" ", 2);
            String description = parts.length > 1 ? parts[1].trim() : "";
            if (description.isEmpty()) {
                throw new InvalidCommandException("The description of a todo cannot be empty.");
            }
            Todo todo = new Todo(description);
            items.add(todo);
            
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
            items.add(deadline);

        } else if (message.startsWith("event")) {
            String[] parts = message.substring(5).split(" /from | /to ");
            if (parts.length < 3) {
                throw new InvalidCommandException("The event command must include /from and /to clauses.");
            }
            String description = parts[0].trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            Event event = new Event(description, from, to);
            items.add(event);
        } else {
            throw new InvalidCommandException("Invalid command.");
        }
    }

    




    public static void main(String[] args) {
        Tom tom = new Tom("data/tasks.txt");
        tom.loadTasks();
        tom.ui.showWelcomeMessage();

        String message = scanner.nextLine();

        while (true) {
            message = message.trim();

            try {
                CommandType type = getCommandType(message);

                switch (type) {
                    case BYE -> {
                        break; // breaks out of switch, but we want to exit loop
                    }
                    case LIST -> tom.items.list_items();
                    case MARK -> tom.items.markDone(parseTaskNumber(message));
                    case UNMARK -> tom.items.markUndone(parseTaskNumber(message));
                    case DELETE -> tom.items.delete(parseTaskNumber(message));
                    case TODO, DEADLINE, EVENT -> tom.parser(message);
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
        }
}
