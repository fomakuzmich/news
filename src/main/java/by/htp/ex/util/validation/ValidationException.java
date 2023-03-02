package by.htp.ex.util.validation;

import by.htp.ex.service.ServiceException;

public class ValidationException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public ValidationException() {
		super();
	}

	public ValidationException(Exception e) {
		super(e);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(String message, Exception e) {
		super(message, e);
	}

}
