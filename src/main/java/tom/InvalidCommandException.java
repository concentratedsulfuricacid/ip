package tom;
/**
 * Represents an exception caused by invalid user commands.
 */
public class InvalidCommandException extends TomException {
    /**
     * Creates an invalid-command exception with the provided message.
     *
     * @param message Exception message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}

