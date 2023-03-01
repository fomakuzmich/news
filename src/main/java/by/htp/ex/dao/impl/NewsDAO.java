package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.dao.connectionpool.ConnectionPool;

public class NewsDAO implements INewsDAO {

	private final ConnectionPool connectionPool = InitPoolListener.getConnectionPool();

	private static final String GET_ALL_NEWS_QUERY = "SELECT * FROM news ORDER BY id DESC";
	private static final String GET_LATEST_NEWS_QUERY = "SELECT * FROM news ORDER BY id DESC LIMIT ?";
	private static final String GET_NEWS_BY_ID_QUERY = "SELECT * FROM news WHERE id = ?";
	private static final String ADD_NEWS_QUERY = "INSERT INTO news (title, brief, content, users_id) VALUES (?, ?, ?, ?)";
	private static final String EDIT_NEWS_QUERY = "UPDATE news SET title = ? , brief = ?, content = ? WHERE id = ?";
	private static final String DELETE_NEWS_QUERY = "DELETE FROM news WHERE id = ?";

	@Override
	public List<News> getLatestsList(int count) throws DaoException {
		List<News> result = new ArrayList<News>();

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = connectionPool.takeConnection();
			statement = connection.prepareStatement(GET_LATEST_NEWS_QUERY);
			statement.setInt(1, count);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				result.add(new News(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getDate(5).toString(), resultSet.getInt(6)));
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);

		} finally {
			if (statement != null) {
				try {
					connectionPool.closeConnection(connection, statement);
					} catch (ConnectionPoolException e) {
						throw new DaoException(e);
					}
			}
		}

		return result;
	}

	@Override
	public List<News> getList() throws DaoException {
		List<News> result = new ArrayList<News>();

		Connection connection = null;
		Statement statement = null;

		try {
			connection = connectionPool.takeConnection();
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(GET_ALL_NEWS_QUERY);

			while (resultSet.next()) {
				result.add(new News(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getDate(5).toString(), resultSet.getInt(6)));
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);

		} finally {
			if (statement != null) {
				try {
					connectionPool.closeConnection(connection, statement);
					} catch (ConnectionPoolException e) {
						throw new DaoException(e);
					}
			}
		}

		return result;
	}

	@Override
	public News fetchById(int id) throws DaoException {

		News news = null;

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = connectionPool.takeConnection();
			statement = connection.prepareStatement(GET_NEWS_BY_ID_QUERY);
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				news = new News(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getDate(5).toString(), resultSet.getInt(6));
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);

		} finally {
			if (statement != null) {
				try {
					connectionPool.closeConnection(connection, statement);
					} catch (ConnectionPoolException e) {
						throw new DaoException(e);
					}
			}
		}

		return news;
	}

	@Override
	public void addNews(News news) throws DaoException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = connectionPool.takeConnection();
			statement = connection.prepareStatement(ADD_NEWS_QUERY);
			statement.setString(1, news.getTitle());
			statement.setString(2, news.getBriefNews());
			statement.setString(3, news.getContent());
			statement.setInt(4, news.getUserId());

			statement.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);

		} finally {
			if (statement != null) {
				try {
					connectionPool.closeConnection(connection, statement);
					} catch (ConnectionPoolException e) {
						throw new DaoException(e);
					}
			}
		}

	}

	@Override
	public void updateNews(News news) throws DaoException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = connectionPool.takeConnection();
			statement = connection.prepareStatement(EDIT_NEWS_QUERY);
			statement.setString(1, news.getTitle());
			statement.setString(2, news.getBriefNews());
			statement.setString(3, news.getContent());
			statement.setInt(4, news.getIdNews());

			statement.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);

		} finally {
			if (statement != null) {
				try {
				connectionPool.closeConnection(connection, statement);
				} catch (ConnectionPoolException e) {
					throw new DaoException(e);
				}
			}
		}

	}

	@Override
	public void deleteNewses(String[] idNewses) throws DaoException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = connectionPool.takeConnection();
			statement = connection.prepareStatement(DELETE_NEWS_QUERY);

			for (String newsId : idNewses) {
				Integer id = Integer.parseInt(newsId);
				statement.setInt(1, id);
				statement.executeUpdate();
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);

		} finally {
			if (statement != null) {
				try {
					connectionPool.closeConnection(connection, statement);
					} catch (ConnectionPoolException e) {
						throw new DaoException(e);
					}
			}
		}

	}

}
