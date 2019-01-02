package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.database.ConnectionUtil;
import model.bean.Xa;

public class XaDao {
	private ConnectionUtil connectionUtil = new ConnectionUtil();
	private String nameXa;
	private String typeXa;
	private String idQuan;
	private String idXa;

	public XaDao() {

	}

	public List<String> getAllNameXaFromIdQuan(String idQuan) {
		String sql = "SELECT * FROM xaphuongthitran WHERE maqh = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<String> listNameXa = new ArrayList<String>();
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idQuan);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				listNameXa.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return listNameXa;
		}
	}

	public List<String> getAllNameXaFromNameQuan(String nameQuan) {
		QuanDao quanDao = new QuanDao();
		idQuan = quanDao.getIdQuanByNameQuan(nameQuan);
		return getAllNameXaFromIdQuan(idQuan);
	}

	public String getNameByIdXa(String idXa) {
		String sql = "SELECT * FROM xaphuongthitran WHERE xaid = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idXa);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				nameXa = resultSet.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return nameXa;
		}
	}

	public String getTypeByIdXa(String idXa) {
		String sql = "SELECT * FROM xaphuongthitran WHERE xaid = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idXa);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				typeXa = resultSet.getString("type");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return typeXa;
		}
	}

	public String getIdQuanByIdXa(String idXa) {
		String sql = "SELECT * FROM xaphuongthitran WHERE xaid = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idXa);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				idQuan = resultSet.getString("maqh");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return idQuan;
		}
	}

	public String getNameQuanByIdXa(String idXa) {
		QuanDao quanDao = new QuanDao();
		return quanDao.getNameByIdQuan(getIdQuanByIdXa(idXa));
	}

	public String getNameThanhPhoByIdXa(String idXa) {
		QuanDao quanDao = new QuanDao();
		String idThanhPho = quanDao.getIdThanhPhoByIdQuan(getIdQuanByIdXa(idXa));
		ThanhPhoDao thanhPhoDao = new ThanhPhoDao();
		return thanhPhoDao.getNameByIdThanhPho(idThanhPho);
	}

	public String getIdXaByNameXa(String nameXa) {
		String sql = "SELECT * FROM xaphuongthitran WHERE name = ? ";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, nameXa);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				idXa = resultSet.getString("xaid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return idXa;
		}
	}
}
