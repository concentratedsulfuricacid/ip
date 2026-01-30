package tom;

/**
 * Represents an exception caused by an invalid task number.
 */
public class InvalidTaskNumberException extends TomException {
    /**
     * Creates an invalid-task-number exception with the provided message.
     *
     * @param message Exception message.
     */
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}
