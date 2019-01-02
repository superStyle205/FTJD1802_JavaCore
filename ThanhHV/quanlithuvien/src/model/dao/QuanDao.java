package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.database.ConnectionUtil;

public class QuanDao {
	private ConnectionUtil connectionUtil = new ConnectionUtil();
	private String id;
	private String nameQuan;
	private String typeQuan;
	private String idThanhPho;

	public QuanDao() {

	}

	public List<String> getAllNameQuanFromIdThanhPho(String idThanhPho) {
		String sql = "SELECT * FROM quanhuyen WHERE matp = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<String> listNameQuan = new ArrayList<String>();
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idThanhPho);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				listNameQuan.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return listNameQuan;
		}
	}

	public List<String> getAllNameQuanFromNameThanhPho(String nameThanhPho) {
		ThanhPhoDao thanhPhoDao = new ThanhPhoDao();
		idThanhPho = thanhPhoDao.getIdThanhPhoByNameThanhPho(nameThanhPho);
		return getAllNameQuanFromIdThanhPho(idThanhPho);
	}

	public String getNameByIdQuan(String idQuan) {
		String sql = "SELECT * FROM quanhuyen WHERE maqh = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idQuan);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				nameQuan = resultSet.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return nameQuan;
		}
	}

	public String getIdQuanByNameQuan(String nameQuan) {
		String sql = "SELECT * FROM quanhuyen WHERE name = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, nameQuan);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getString("maqh");
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

	public String getNameByIdXa(String idXa) {
		return getNameByIdQuan((new XaDao()).getIdQuanByIdXa(idXa));
	}

	public String getTypeByIdQuan(String idQuan) {
		String sql = "SELECT * FROM quanhuyen WHERE maqh = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idQuan);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				typeQuan = resultSet.getString("type");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return typeQuan;
		}
	}

	public String getIdThanhPhoByIdQuan(String idQuan) {
		String sql = "SELECT * FROM quanhuyen WHERE maqh = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idQuan);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				idThanhPho = resultSet.getString("matp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return idThanhPho;
		}

	}

}
