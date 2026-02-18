package tom;

/**
 * Represents a response from Tom, including whether it is an error.
 */
public class TomResponse {
    private final String message;
    private final boolean isError;

    /**
     * Creates a response with a message and error flag.
     *
     * @param message Response message text.
     * @param isError True if the response represents an error.
     */
    public TomResponse(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return isError;
    }
}
