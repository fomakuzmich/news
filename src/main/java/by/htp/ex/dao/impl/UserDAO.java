package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.InitPoolListener;

public class UserDAO implements IUserDAO {

	private ConnectionPool connectionPool = InitPoolListener.getConnectionPool();

	private static final String GET_ROLE_QUERY = "SELECT * FROM roles WHERE id = ?";
	private static final String CLIENT_ROLES_ID = "2";
	private static final String QUOTE = "'";

	@Override
	public NewUserInfo logination(String email, String password) throws DaoException {

		String loginationQuery = createLoginationQuery(email, password);

		Connection connection = null;
		Statement statement = null;
		NewUserInfo user = null;

		try {
			connection = connectionPool.takeConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(loginationQuery);

			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				String email1 = resultSet.getString(5);
				int roles_id = resultSet.getInt(7);
				user = new NewUserInfo(email1, id, password, getRole(roles_id));
			}

		} catch (Exception e) {
			throw new DaoException(e);

		} finally {
			if (statement != null) {
				try {
					connectionPool.closeConnection(connection, statement);
				} catch (Exception e) {
					throw new DaoException(e);
				}
			}
		}

		return user;
	}

	public String getRole(int id) throws DaoException {

		Connection connection = null;
		PreparedStatement statement = null;
		String role = "";
		try {
			connection = connectionPool.takeConnection();
			statement = connection.prepareStatement(GET_ROLE_QUERY);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				role = resultSet.getString(2);
			}

		} catch (Exception e) {
			throw new DaoException(e);

		} finally {
			if (statement != null) {
				try {
					connectionPool.closeConnection(connection, statement);
				} catch (Exception e) {
					throw new DaoException(e);
				}
			}
		}

		return role;
	}

	@Override
	public boolean registration(NewUserInfo user) throws DaoException {

		String addUserQuery = createAddUserQuery(user);

		Connection connection = null;
		Statement statement = null;

		try {

			connection = connectionPool.takeConnection();
			statement = connection.createStatement();
			statement.executeUpdate(addUserQuery);

		} catch (Exception e) {
			throw new DaoException(e);

		} finally {

			if (statement != null) {
				try {
					connectionPool.closeConnection(connection, statement);
				} catch (Exception e) {
					throw new DaoException(e);
				}
			}
		}

		return true;
	}

	private String createLoginationQuery(String email, String password) {

		StringBuilder sb = new StringBuilder(150);
		sb.append("SELECT * FROM users WHERE email = ").append(QUOTE).append(email).append(QUOTE)
				.append(" AND password = ").append(QUOTE).append(password).append(QUOTE);

		return sb.toString();
	}

	private String createAddUserQuery(NewUserInfo user) {
		String id = "" + user.getId();
		String password = user.getPassword();
		String email = user.getEmail();

		String addUserQuery = "INSERT INTO users (id, password, email, roles_id ) " + "VALUES (" + "'" + id + "', "
				+ "'" + password + "', " + "'" + email + "', " + "'" + CLIENT_ROLES_ID + "')";
		return addUserQuery;
	}

}
