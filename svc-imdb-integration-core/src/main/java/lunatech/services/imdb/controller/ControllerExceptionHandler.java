package lunatech.services.imdb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;
import lunatech.services.imdb.exception.ExceptionEnum;
import lunatech.services.imdb.exception.ServiceProcessingException;
import lunatech.services.model.imdb.ApplicationError;
import static lunatech.services.imdb.utils.ApiConstants.LOG_ERR_PLACEHOLDER;

/**
 * Class to handle exceptions across project. 1) Spring Validation Exception 2)
 * Project specific exceptions {@link ServiceProcessingException} 3) Generic
 * Exception
 * 
 * @author hari
 *
 */

@Slf4j
@ControllerAdvice(value = "lunatech.services.imdb")
public class ControllerExceptionHandler {

	@Autowired
	HttpServletRequest request;

	/**
	 * Handler for invalid input fields
	 *
	 * @param ex Validation exception
	 * @return
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	private ApplicationError handleMissingInputInBody(Exception ex) {

		log.error(LOG_ERR_PLACEHOLDER, getRequestId(), ex.getMessage());

		return errorBuilder(ExceptionEnum.INVALID_INPUT.findErrorCode(),
				ExceptionEnum.INVALID_INPUT.findErrorMessage());
	}

	/**
	 * Handler for missing parameters in request response
	 *
	 * @param ex Missing Parameter exception
	 * @return
	 */
	@ExceptionHandler(MissingRequestHeaderException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	private ApplicationError handleMissingHeader(MissingRequestHeaderException ex) {
		log.error(LOG_ERR_PLACEHOLDER, getRequestId(), ex.getMessage());
		return errorBuilder(ExceptionEnum.MISSING_HEADER.findErrorCode(),
				ExceptionEnum.MISSING_HEADER.findErrorMessage().concat(ex.getHeaderName()));
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	private ApplicationError handleMissingInput(MissingServletRequestParameterException ex) {
		log.error(LOG_ERR_PLACEHOLDER, getRequestId(), ex.getMessage());
		return errorBuilder(ExceptionEnum.MISSING_FIELD.findErrorCode(),
				ExceptionEnum.MISSING_FIELD.findErrorMessage());
	}

	/**
	 * Method to capture generic exception and return message response
	 *
	 * @param ex Missing Parameter exception
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	private ApplicationError handleGenericException(Exception ex) {
		log.error(LOG_ERR_PLACEHOLDER, getRequestId(), ex.getMessage());
		return errorBuilder(ExceptionEnum.EX_GENERAL.findErrorCode(), ExceptionEnum.EX_GENERAL.findErrorMessage());

	}

	private static ApplicationError errorBuilder(String errCode, String errMsg) {
		ApplicationError applicationError = new ApplicationError();
		applicationError.setErrorCode(errCode);
		applicationError.setErrorMessage(errMsg);
		return applicationError;
	}

	private String getRequestId() {
		return request.getHeader("x-request-id");
	}
}
