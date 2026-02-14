package tom;

import tom.command.AddCommand;
import tom.command.Command;
import tom.command.DeleteCommand;
import tom.command.ExitCommand;
import tom.command.FindCommand;
import tom.command.ListCommand;
import tom.command.MarkDoneCommand;
import tom.command.UnmarkDoneCommand;
import tom.task.Deadline;
import tom.task.Event;
import tom.task.Todo;

/**
 * Parses user input into executable commands.
 */
public class Parser {

    private enum CommandType {
        LIST, BYE, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, INVALID, FIND;

        public static CommandType fromString(String command) {
            try {
                return CommandType.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return CommandType.INVALID;
            }
        }
    }

    /**
     * Returns an add command for a todo parsed from the user input.
     *
     * @param message Full user input line.
     * @return The parsed add command.
     * @throws TomException If the todo description is missing.
     */
    public static Command parseTodo(String message) throws TomException {
        assert message != null : "message should not be null";
        assert message.startsWith("todo") : "parseTodo expects input starting with 'todo'";
        String[] parts = message.split(" ", 2);
        String description = parts.length > 1 ? parts[1].trim() : "";
        if (description.isEmpty()) {
            throw new InvalidCommandException("The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(description);
        return new AddCommand(todo);
    }

    /**
     * Returns an add command for a deadline parsed from the user input.
     *
     * @param message Full user input line.
     * @return The parsed add command.
     * @throws TomException If the deadline input is missing or malformed.
     */
    public static Command parseDeadline(String message) throws TomException {
        assert message != null : "message should not be null";
        assert message.startsWith("deadline") : "parseDeadline expects input starting with 'deadline'";
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

    /**
     * Returns an add command for an event parsed from the user input.
     *
     * @param message Full user input line.
     * @return The parsed add command.
     * @throws TomException If the event input is missing or malformed.
     */
    public static Command parseEvent(String message) throws TomException {
        assert message != null : "message should not be null";
        assert message.startsWith("event") : "parseEvent expects input starting with 'event'";
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

    /**
     * Returns the task number parsed from the user input.
     *
     * @param message Full user input line.
     * @return The parsed task number.
     * @throws TomException If the task number is missing or invalid.
     */
    private static int parseTaskNumber(String message) throws TomException {
        assert message != null : "message should not be null";
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

    /**
     * Returns a mark-as-done command parsed from the user input.
     *
     * @param message Full user input line.
     * @return The parsed mark-as-done command.
     * @throws TomException If the task number is missing or invalid.
     */
    public static Command parseMarkCommand(String message) throws TomException {
        int taskNumber = parseTaskNumber(message);
        return new MarkDoneCommand(taskNumber);
    }

    /**
     * Returns an unmark command parsed from the user input.
     *
     * @param message Full user input line.
     * @return The parsed unmark command.
     * @throws TomException If the task number is missing or invalid.
     */
    public static Command parseUnmarkCommand(String message) throws TomException {
        int taskNumber = parseTaskNumber(message);
        return new UnmarkDoneCommand(taskNumber);
    }

    /**
     * Returns a delete command parsed from the user input.
     *
     * @param message Full user input line.
     * @return The parsed delete command.
     * @throws TomException If the task number is missing or invalid.
     */
    public static Command parseDeleteCommand(String message) throws TomException {
        int taskNumber = parseTaskNumber(message);
        return new DeleteCommand(taskNumber);
    }

    /**
     * Returns a list command.
     *
     * @return The list command.
     */
    public static Command parseListCommand() {
        return new ListCommand();
    }

    /**
     * Returns an exit command.
     *
     * @return The exit command.
     */
    public static Command parseByeCommand() {
        return new ExitCommand();
    }

    /**
     * Returns a find command parsed from the user input.
     *
     * @param message Full user input line.
     * @return The parsed find command.
     * @throws TomException If the keyword is missing.
     */
    public static Command parseFindCommand(String message) throws TomException {
        assert message != null : "message should not be null";
        assert message.startsWith("find") : "parseFindCommand expects input starting with 'find'";
        String[] parts = message.trim().split("\\s+", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new InvalidCommandException("Please provide a keyword to find.");
        }
        String keyword = parts[1].trim();
        return new FindCommand(keyword);
    }

    /**
     * Returns the command parsed from the full user input line.
     *
     * @param message Full user input line.
     * @return The parsed command.
     * @throws TomException If the command cannot be parsed.
     */
    public static Command parse(String message) throws TomException {
        assert message != null : "message should not be null";

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
            case FIND:
                return parseFindCommand(message);
            default:
                throw new InvalidCommandException("Invalid command.");
        }
    }
}
