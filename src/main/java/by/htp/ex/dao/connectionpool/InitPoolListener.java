package by.htp.ex.dao.connectionpool;

import jakarta.servlet.ServletContextEvent;

public class InitPoolListener {

	public static ConnectionPool connectionPool = new ConnectionPool();

	public void contextInitialized(ServletContextEvent event) {

		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		}

	}

	public void contextDestroyed(ServletContextEvent event) throws ConnectionPoolException {

		connectionPool.dispose();
	}

	public static ConnectionPool getConnectionPool() {
		return connectionPool;
	}

}
