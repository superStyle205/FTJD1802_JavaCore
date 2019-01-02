package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.database.ConnectionUtil;
import model.bean.Members;

public class ThanhPhoDao {
	private ConnectionUtil connectionUtil = new ConnectionUtil();
	private String id;
	private String nameThanhPho;
	private String typeThanhPho;

	public ThanhPhoDao() {

	}

	public List<String> getAllNameThanhPho() {
		String sql = "SELECT * FROM tinhthanhpho ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<String> listNameThanhPho = new ArrayList<String>();
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				listNameThanhPho.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return listNameThanhPho;
		}
	}

	public String getIdThanhPhoByNameThanhPho(String nameThanhPho) {
		String sql = "SELECT * FROM tinhthanhpho WHERE name = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, nameThanhPho);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getString("matp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return id;
		}
	}

	public String getNameByIdThanhPho(String idThanhPho) {
		String sql = "SELECT * FROM tinhthanhpho WHERE matp = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idThanhPho);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				nameThanhPho = resultSet.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return nameThanhPho;
		}
	}

	public String getTypeByIdThanhPho(String idThanhPho) {
		String sql = "SELECT * FROM tinhthanhpho WHERE matp = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idThanhPho);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				typeThanhPho = resultSet.getString("type");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return typeThanhPho;
		}
	}

}
