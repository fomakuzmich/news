package by.htp.ex.service.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationException;
import by.htp.ex.util.validation.ValidationProvider;
import by.htp.ex.service.IUserService;

public class UserServiceImpl implements IUserService {

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
	private final UserDataValidation validator = ValidationProvider.getInstance().getUserDataValidator();

	@Override
	public NewUserInfo signIn(String email, String password) throws ServiceException {

		NewUserInfo user = null;

		try {
			user = userDAO.logination(email, password);

		} catch (DaoException e) {
			throw new ServiceException(e);
		}

		return user;
	}

	@Override
	public boolean registration(NewUserInfo userInfo) throws ServiceException {

		String email = userInfo.getEmail();
		String password = userInfo.getPassword();

		try {
			validator.checkUserData(email, password);
		} catch (ValidationException e) {
			throw new ServiceException("validation exception", e);
		}

		try {
			userDAO.registration(userInfo);
		} catch (DaoException e) {
			throw new ServiceException("dao exception", e);
		}
		return true;
	}

}
