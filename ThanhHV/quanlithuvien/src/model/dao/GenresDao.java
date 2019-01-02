package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.database.ConnectionUtil;
import model.bean.Books;
import model.bean.Genres;

public class GenresDao {
	private ConnectionUtil connectionUtil = new ConnectionUtil();
	private String name;
	private String id;

	public GenresDao() {

	}

	public String getNameGenresById(String id) {
		String sql = "SELECT * FROM theloai WHERE matheloai = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				this.name = resultSet.getString("tentheloai");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return this.name;
		}
	}

	public String getIdGenresByName(String name) {
		String sql = "SELECT * FROM theloai WHERE tentheloai = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				this.id = resultSet.getString("matheloai");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return this.id;
		}
	}

	public boolean removeGenresById(String id) {
		String sql = "UPDATE theloai SET deleted = ? WHERE  matheloai = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setString(2, id);
			status = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeStatement(statement);
			connectionUtil.closeConnection(connection);
		}
		return status > 0 ? true : false;
	}

	public List<String> getAllNameGenres() {
		String sql = "SELECT * FROM theloai WHERE deleted = 0";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<String> listName = new ArrayList<String>();
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				listName.add(resultSet.getString("tentheloai"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return listName;
		}
	}

	public List<String> getAllGenresd() {
		String sql = "SELECT * FROM theloai WHERE deleted = 0";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<String> listId = new ArrayList<String>();
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				listId.add(resultSet.getString("matheloai"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return listId;
		}
	}

	public boolean updateGenres(Genres genres) {
		String sql = "UPDATE theloai SET tentheloai = ? WHERE  matheloai = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, genres.getName());
			statement.setString(2, genres.getId());
			status = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeStatement(statement);
			connectionUtil.closeConnection(connection);
		}
		return status > 0 ? true : false;
	}

	public boolean addGenres(Genres genres) {
		String sql = "INSERT INTO theloai VALUES(?, ?, ?)";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, genres.getId());
			statement.setString(2, genres.getName());
			statement.setInt(3, 0);
			status = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeStatement(statement);
			connectionUtil.closeConnection(connection);
		}
		return status > 0 ? true : false;
	}

	public boolean checkIdExisted(String id) {
		String sql = "SELECT * FROM theloai WHERE matheloai = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean result = true;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			resultSet = statement.executeQuery();
			result = resultSet.next();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return result;
		}
	}
}
