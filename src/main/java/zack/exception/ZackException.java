package zack.exception;

/**
 * Represents an exception specific to the Zack application.
 */
public class ZackException extends Exception {
    /**
     * Constructs a ZackException with the given error message.
     *
     * @param message Error message.
     */
    public ZackException(String message) {
        super(message);
    }
}
