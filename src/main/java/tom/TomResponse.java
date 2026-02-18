package tom;

/**
 * Represents a response from Tom, including whether it is an error.
 */
public class TomResponse {
    private final String message;
    private final boolean isError;

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
