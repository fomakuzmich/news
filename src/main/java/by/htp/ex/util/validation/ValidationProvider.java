package by.htp.ex.util.validation;

public class ValidationProvider {

	private static final ValidationProvider instance = new ValidationProvider();
	private final UserDataValidation userValidator = new UserDataValidationImpl();

	private ValidationProvider() {

	}

	public static ValidationProvider getInstance() {
		return instance;
	}

	public UserDataValidation getUserDataValidator() {
		return userValidator;
	}

}
