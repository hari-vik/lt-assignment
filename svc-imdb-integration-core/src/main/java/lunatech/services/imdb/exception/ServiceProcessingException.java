package lunatech.services.imdb.exception;

/**
 * Custom exception class to handle error across projects
 * @author hari
 *
 */
public class ServiceProcessingException extends Exception {

    private static final long serialVersionUID = 1441201016393884947L;
    protected String errorCode;
    protected String errorMessage;

    public ServiceProcessingException() {
        super();
    }

    public ServiceProcessingException(String message) {
        super(message);
    }

    public ServiceProcessingException(Throwable cause) {
        super(cause);
    }

    public ServiceProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceProcessingException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ServiceProcessingException(String errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}

