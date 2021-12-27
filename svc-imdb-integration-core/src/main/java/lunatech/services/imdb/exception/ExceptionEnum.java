package lunatech.services.imdb.exception;

/**
 * Enum class to configure error codes and message for exceptions
 * @author hari
 *
 */
public enum ExceptionEnum {

	EX_GENERAL("SVC5000", "General Server exception"),
	INVALID_INPUT("SVC4001", "Invalid input parameter"),
	MISSING_FIELD("SVC4002", "Missing required field"),
	MISSING_HEADER("SVC4003", "Missing required header ");


	private String errorCode;
	private String errorMessagePrefix;

	ExceptionEnum(String errorCode, String errorMessagePrefix) {
		this.errorCode = errorCode;
		this.errorMessagePrefix = errorMessagePrefix;
	}

	public String findErrorCode() {
		return String.valueOf(errorCode);
	}

	public String findErrorMessage() {
		return String.valueOf(errorMessagePrefix);
	}
}
