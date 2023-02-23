package by.htp.ex.service;

public class ServiceException extends Exception {

	public ServiceException() {
		super();
	}

	public ServiceException(String e) {
		super(e);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String message, Exception e) {
		super(message, e);
	}
}
