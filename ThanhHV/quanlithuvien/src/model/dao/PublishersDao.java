package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.database.ConnectionUtil;
import model.bean.Authors;
import model.bean.Publishers;

public class PublishersDao {
	private ConnectionUtil connectionUtil = new ConnectionUtil();
	private String name;
	private String id;

	public PublishersDao() {

	}

	public String getNamePublishersById(String id) {
		String sql = "SELECT * FROM nhaxuatban WHERE manxb = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				this.name = resultSet.getString("tennxb");
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

	public String getIdPublishersByName(String name) {
		String sql = "SELECT * FROM nhaxuatban WHERE tennxb = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				this.id = resultSet.getString("manxb");
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

	public boolean removePublishersById(String id) {
		String sql = "UPDATE nhaxuatban SET deleted = ? WHERE  manxb = ?";
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

	public List<String> getAllNamePublishers() {
		String sql = "SELECT * FROM nhaxuatban WHERE deleted = 0";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<String> listName = new ArrayList<String>();
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				listName.add(resultSet.getString("tennxb"));
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

	public List<String> getAllIdPublishers() {
		String sql = "SELECT * FROM nhaxuatban WHERE deleted = 0";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<String> listId = new ArrayList<String>();
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				listId.add(resultSet.getString("manxb"));
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

	public boolean updatePublishers(Publishers publishers) {
		String sql = "UPDATE nhaxuatban SET tennxb = ? WHERE  manxb= ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, publishers.getName());
			statement.setString(2, publishers.getId());
			status = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeStatement(statement);
			connectionUtil.closeConnection(connection);
		}
		return status > 0 ? true : false;
	}

	public boolean addPublishers(Publishers publishers) {
		String sql = "INSERT INTO nhaxuatban VALUES(?, ?, ?)";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, publishers.getId());
			statement.setString(2, publishers.getName());
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
		String sql = "SELECT * FROM nhaxuatban WHERE manxb = ?";
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
