package common.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionUtil {
	private static final String HOST_NAME = "localhost";
	private static final String PORT = "3306";
	private static final String DATABASE_NAME = "quanlithuvien";
	private static final String URL_CONNECT = "jdbc:mysql://" + HOST_NAME + ":" + PORT + "/" + DATABASE_NAME;
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private static Properties properties = new Properties();

	public ConnectionUtil() {

	}

	static {
		properties.setProperty("user", USER);
		properties.setProperty("password", PASSWORD);
		properties.setProperty("characterEncoding", "UTF-8");
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL_CONNECT, properties);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
