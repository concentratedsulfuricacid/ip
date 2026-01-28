package tom;
import tom.command.AddCommand;
import tom.command.Command;
import tom.command.DeleteCommand;
import tom.command.ExitCommand;
import tom.command.ListCommand;
import tom.command.MarkDoneCommand;
import tom.command.UnmarkDoneCommand;
import tom.task.Deadline;
import tom.task.Event;
import tom.task.Todo;


public class Parser {

    private enum CommandType {
        LIST, BYE, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, INVALID;

        public static CommandType fromString(String command) {
            try {
                return CommandType.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return CommandType.INVALID;
            }
        }
    }

    public static Command parseTodo(String message) throws TomException {
        String[] parts = message.split(" ", 2);
            String description = parts.length > 1 ? parts[1].trim() : "";
            if (description.isEmpty()) {
                throw new InvalidCommandException("The description of a todo cannot be empty.");
            }
            Todo todo = new Todo(description);
            return new AddCommand(todo);
    }

    public static Command parseDeadline(String message) throws TomException {
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
        return new AddCommand(deadline);
    }

    public static Command parseEvent(String message) throws TomException {
        String[] parts = message.substring(5).split(" /from | /to ");
        if (parts.length < 3) {
            throw new InvalidCommandException("The event command must include /from and /to clauses.");
        }
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        Event event = new Event(description, from, to);
        return new AddCommand(event);
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

    public static Command parseMarkCommand(String message) throws TomException {
        int taskNumber = parseTaskNumber(message);
        return new MarkDoneCommand(taskNumber);
    }

    public static Command parseUnmarkCommand(String message) throws TomException {
        int taskNumber = parseTaskNumber(message);
        return new UnmarkDoneCommand(taskNumber);
    }
    public static Command parseDeleteCommand(String message) throws TomException {
        int taskNumber = parseTaskNumber(message);
        return new DeleteCommand(taskNumber);
    }

    public static Command parseListCommand() {
        return new ListCommand();
    }

    public static Command parseByeCommand() {
        return new ExitCommand();
    }

    public static Command parse(String message) throws TomException {

        String firstWord = message.trim().split("\\s+")[0].toLowerCase();

        if (firstWord.isEmpty()) {
            throw new InvalidCommandException("Please enter a command.");
        }

        CommandType type = CommandType.fromString(firstWord);

        switch (type) {
            case TODO:
                return parseTodo(message);
            case DEADLINE:
                return parseDeadline(message);
            case EVENT:
                return parseEvent(message);
            case MARK:
                return parseMarkCommand(message);
            case UNMARK:
                return parseUnmarkCommand(message);
            case DELETE:
                return parseDeleteCommand(message);
            case LIST:
                return parseListCommand();
            case BYE:
                return parseByeCommand();
            default:
                throw new InvalidCommandException("Invalid command.");
        }
    }
}
