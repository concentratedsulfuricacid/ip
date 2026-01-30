package tom;

/**
 * Represents an exception raised by the Tom application.
 */
public class TomException extends Exception {
    /**
     * Creates a Tom exception with the provided message.
     *
     * @param message Exception message.
     */
    public TomException(String message) {
        super(message);
    }

}
