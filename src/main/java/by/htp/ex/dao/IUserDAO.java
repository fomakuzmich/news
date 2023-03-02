package by.htp.ex.dao;

import by.htp.ex.bean.NewUserInfo;

public interface IUserDAO {

	NewUserInfo logination(String email, String password) throws DaoException;

	boolean registration(NewUserInfo user) throws DaoException;

	public String getRole(int id) throws DaoException;

}
