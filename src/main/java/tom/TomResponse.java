package tom;

/**
 * Represents a response from Tom, including whether it is an error.
 */
public class TomResponse {
    private final String message;
    private final boolean isError;
    private final boolean isExit;

    /**
     * Creates a response with a message and error flag.
     *
     * @param message Response message text.
     * @param isError True if the response represents an error.
     * @param isExit True if the response should terminate the app.
     */
    public TomResponse(String message, boolean isError, boolean isExit) {
        this.message = message;
        this.isError = isError;
        this.isExit = isExit;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Returns whether the response represents an error.
     *
     * @return True if the response is an error.
     */
    public boolean isError() {
        return isError;
    }

    /**
     * Returns whether the response should terminate the application.
     *
     * @return True if the response signals exit.
     */
    public boolean isExit() {
        return isExit;
    }
}
